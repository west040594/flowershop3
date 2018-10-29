package com.accenture.be.business.order.implement;

import com.accenture.be.access.order.OrderDAO;
import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.cart.CartItem;
import com.accenture.be.business.customer.converters.CustomerConverter;
import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.business.order.converters.OrderConverter;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.order.validators.CreateOrderValidator;
import com.accenture.be.business.orderproduct.converters.OrderProductConverter;
import com.accenture.be.business.orderproduct.interfaces.OrderProductService;
import com.accenture.be.business.product.converters.ProductConverter;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.be.entity.product.Product;
import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.order.OrderDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;
import com.accenture.fe.dto.user.UserDTO;
import com.accenture.fe.enums.order.OrderStatus;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private CreateOrderValidator createOrderValidator;

    @Override
    public List<OrderDTO> findAllOrder() {
        return OrderConverter.convertToDTO(orderDAO.findAll());
    }

    @Transactional
    @Override
    public Order saveOrder(Order order) {
        Long orderId = orderDAO.save(order);
        return orderDAO.findById(orderId);
    }

    @Transactional
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

            //Берем предметы из корзины и формируем новые OrderProducts
            List<OrderProduct> orderProducts = new ArrayList<>();
            for (CartItem cartItem : orderDTO.getCustomer().getCart().getItemList()) {
                Product product = ProductConverter.convertToEntity(cartItem.getProduct());
                orderProducts.add(new OrderProduct(product, order, cartItem.getQuantity()));
            }
            order.setOrderProducts(orderProducts);

            //Очищаем корзину и сохраняем заказ
            orderDTO.getCustomer().getCart().removeAllItem();
            order = saveOrder(order);
            orderProductService.saveOrderProducts(orderProducts);

            //Возвращаем orderDTO
            OrderDTO newOrderDTO = OrderConverter.convertToDTO(order);
            newOrderDTO.getCustomer().setCart(orderDTO.getCustomer().getCart());
            return newOrderDTO;
        }
    }
    @Override
    public Order getOrderById(long orderId) {
        return orderDAO.findById(orderId);
    }


    @Transactional
    @Override
    public void changerOrderStatusToPaid(UserDTO userDTO, Long orderId) {
        //Изменяем дату закрытия заказа и статус в  -  Закрыто
        Order order = orderDAO.findById(orderId);
        order.setStatus(OrderStatus.PAID);
        order.setClosetAt(new Date());
        orderDAO.update(order);
        //Снимаем деньги с покупателя и устанавливаем и обновляем сессиию покупаетеля
        Customer customer = customerService.withdrawFromBalance(order.getTotal(), order.getCustomer().getId());
        CustomerDTO customerDTO = CustomerConverter.convertToDTO(customer);
        customerDTO.setCart(userDTO.getCustomer().getCart());
        userDTO.setCustomer(customerDTO);
        //Изменяем число "В наличиии" у продуктов
        List<OrderProduct> orderProducts = orderProductService.findOrderProductByOrder(order);
        for (OrderProduct orderProduct : orderProducts) {
            productService.changeProductQuantityInStock(
                    orderProduct.getProduct().getId(), orderProduct.getQuantity());
        }
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
