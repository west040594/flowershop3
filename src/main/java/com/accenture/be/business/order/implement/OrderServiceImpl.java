package com.accenture.be.business.order.implement;

import com.accenture.be.access.order.OrderDAO;
import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.cart.CartItem;
import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.order.validators.CreateOrderValidator;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.enums.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CreateOrderValidator createOrderValidator;

    @Override
    public List<Order> findAllOrder() {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws OrderException {

        //Валидация формы заказа
        StringBuilder errors = new StringBuilder();
        DataBinder dataBinder = new DataBinder(orderDTO);
        dataBinder.addValidators(createOrderValidator);
        dataBinder.validate();

        //Если валидация не прошла, то выкидываем сформированную строку ошибок на сервлет
        if(dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> errors.append(e.getDefaultMessage()).append("<br/>"));

            throw new OrderException(errors.toString());
            //Иначе берем общую стоимость, назначаем статус заказа и дату создания
            //После сохраняем заказ
        } else {
            Order order = OrderConverter.convertToEntity(orderDTO);
            order.setTotal(orderDTO.getCustomer().getCart().getTotal());
            order.setStatus(OrderStatus.CREATED);
            order.setCreatedAt(new Date());
            order.setDeliveryAddress(formDeliveryAddress(order.getCustomer()));
            customerService.withdrawFromBalance(order.getTotal(), order.getCustomer().getId());

            Long orderId = orderDAO.save(order);

            // TODO: 25.10.2018 Взять предметы из корзины и сформировать orderProduct
            return OrderConverter.convertToDTO(orderDAO.findById(orderId));
        }
    }
    @Override
    public Order getOrderById(long orderId) {
        return orderDAO.findById(orderId);
    }

    @Override
    public String formDeliveryAddress(Customer customer) {
        StringBuilder deliveryAddress = new StringBuilder();
        deliveryAddress.append("Имя: " + customer.getFirstName() + ". ")
                .append("Фамилия: " + customer.getLastName()+ ". ")
                .append("Телефон: " + customer.getPhoneNumber()+ ". ")
                .append("Улица: " + customer.getStreet()+ ". ")
                .append("Город: " + customer.getCity()+ ". ")
                .append("Страна: " + customer.getCountry()+ ". ");
        return  deliveryAddress.toString();
    }
}
