package com.accenture.be.business.order.implement;

import com.accenture.be.business.cart.CartItem;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.order.validators.CreateOrderValidator;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.be.entity.product.Product;
import com.accenture.be.repository.OrderRepository;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.enums.order.OrderStatus;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderDAO;

    @Autowired
    private ProductService productService;

    @Autowired
    private CreateOrderValidator createOrderValidator;

    @Autowired
    private Mapper mapper;

    @Override
    public List<Order> findAllOrder() {
        return (List<Order>) orderDAO.findAll();
    }

    @Transactional
    @Override
    public Order saveOrder(Order order) {
        return orderDAO.save(order);
    }

    @Transactional
    @Override
    public Order createOrder(OrderDTO orderDTO) throws OrderException {
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
            Order order = mapper.map(orderDTO, Order.class);
            order.setTotal(orderDTO.getCustomer().getCart().getTotal());
            order.setStatus(OrderStatus.CREATED);
            order.setCreatedAt(new Date());
            order.setDeliveryAddress(formDeliveryAddress(order.getCustomer()));

            //Берем предметы из корзины и формируем новые OrderProducts
            List<OrderProduct> orderProducts = new ArrayList<>();
            for (CartItem cartItem : orderDTO.getCustomer().getCart().getItemList()) {
                Product product = mapper.map(cartItem.getProduct(), Product.class);
                orderProducts.add(new OrderProduct(product, order, cartItem.getQuantity()));
            }
            //Устанавливаем заказу его orderProducts для сохранения строк в бд и сохраняем заказ
            order.setOrderProducts(orderProducts);
            order = saveOrder(order);
            log.debug("Order with id = {} was created. Total = {}",
                    order.getId(), order.getTotal());
            return order;
        }
    }
    @Override
    public Order getOrderById(long orderId) {
        return orderDAO.findById(orderId).get();
    }

    @Transactional
    @Override
    public Order changerOrderStatusToPaid(Long orderId) {
        //Изменяем дату закрытия заказа и статус в  -  Закрыто
        Order order = orderDAO.findById(orderId).get();
        order.setStatus(OrderStatus.PAID);

        //Изменяем число "В наличиии" у продуктов
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            productService.changeProductQuantityInStock(
                    orderProduct.getProduct().getId(), orderProduct.getQuantity());
        }

        //Снимаем деньги с покупателя и обновляем заказ
        order.getCustomer().setBalance(order.getCustomer().getBalance().subtract(order.getTotal()));

        log.debug("Order with id = {} changed status to {}",
                order.getId(), order.getStatus());
        return orderDAO.save(order);
    }

    @Transactional
    @Override
    public Order changeOrderStatusToClosed(Long orderId) {
        Order order = orderDAO.findById(orderId).get();
        order.setStatus(OrderStatus.CLOSED);
        order.setClosetAt(new Date());
        log.debug("Order with id = {} changed status to {}",
                order.getId(), order.getStatus());
        return orderDAO.save(order);
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
