package com.accenture.be.business.order.implement;

import com.accenture.be.business.cart.CartItem;
import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.business.order.exceptions.OrderException;
import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.be.business.order.validators.CreateOrderValidator;
import com.accenture.be.business.product.exceptions.ProductException;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.order.Order;
import com.accenture.be.entity.orderproduct.OrderProduct;
import com.accenture.be.entity.product.Product;
import com.accenture.be.repository.OrderRepository;
import com.accenture.fe.dto.order.OrderForm;
import com.accenture.fe.enums.order.OrderStatus;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderRepository orderDAO;
    private ProductService productService;
    private CustomerService customerService;
    private CreateOrderValidator createOrderValidator;
    private Mapper mapper;

    public OrderServiceImpl(OrderRepository orderDAO,
                            CustomerService customerService, ProductService productService,
                            CreateOrderValidator createOrderValidator, Mapper mapper) {
        this.orderDAO = orderDAO;
        this.customerService = customerService;
        this.productService = productService;
        this.createOrderValidator = createOrderValidator;
        this.mapper = mapper;
    }

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
    public Order createOrder(OrderForm orderForm) throws OrderException {
        //Валидация формы заказа
        StringBuilder errors = new StringBuilder();
        DataBinder dataBinder = new DataBinder(orderForm);
        dataBinder.addValidators(createOrderValidator);
        dataBinder.validate();

        //Если валидация не прошла, то выкидываем сформированную строку ошибок на сервлет
        if(dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> errors.append(e.getDefaultMessage()).append("<br/>"));

            throw new OrderException(errors.toString());
            //Иначе берем общую стоимость, назначаем статус заказа и дату создания и покупателя
            //После сохраняем заказ
        } else {
            Order order = new Order();
            order.setTotal(orderForm.getCart().getTotal());
            order.setStatus(OrderStatus.CREATED);
            order.setCreatedAt(new Date());
            order.setDeliveryAddress(formDeliveryAddress(orderForm));
            order.setCustomer(customerService.findCustomerById(orderForm.getCustomerId()));

            //Берем предметы из корзины и формируем новые OrderProducts
            List<OrderProduct> orderProducts = new ArrayList<>();
            for (CartItem cartItem : orderForm.getCart().getItemList()) {
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
    public Order getOrderById(long orderId) throws OrderException {
        return orderDAO.findById(orderId).orElseThrow(() ->  new OrderException("Заказ не найден"));
    }

    @Transactional(rollbackFor = OrderException.class)
    @Override
    public Order changerOrderStatusToPaid(Long orderId) throws OrderException {
        StringBuilder errors = new StringBuilder();
        Order order = getOrderById(orderId);
        //Изменяем число "В наличиии" у продуктов
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            try {
                productService.changeProductQuantityInStock(
                        orderProduct.getProduct().getId(), orderProduct.getQuantity());
            } catch (ProductException e) {
                errors.append(e.getMessage()).append("<br/>");
            }
        }
        //Если ошибок не возникло снимаем деньги с покупателя и меняем заказ в - Оплачен
        //Иначе выкидываем ошибку, что требуемого количества продукта недостаточно
        if(errors.toString().isEmpty()) {
            order.setStatus(OrderStatus.PAID);
            order.getCustomer().setBalance(order.getCustomer().getBalance().subtract(order.getTotal()));
            log.debug("Order with id = {} changed status to {}",
                    order.getId(), order.getStatus());
            return orderDAO.save(order);
        } else  {
            throw new OrderException(errors.toString());
        }
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
    public String formDeliveryAddress(OrderForm orderForm) {
        StringBuilder deliveryAddress = new StringBuilder();
        deliveryAddress.append("Имя: " + orderForm.getFirstName() + ". ")
                .append("Фамилия: " + orderForm.getLastName()+ ". ")
                .append("Телефон: " + orderForm.getPhoneNumber()+ ". ")
                .append("Улица: " + orderForm.getStreet()+ ". ")
                .append("Город: " + orderForm.getCity()+ ". ")
                .append("Страна: " + orderForm.getCountry()+ ". ");
        return  deliveryAddress.toString();
    }

    @Override
    public Boolean orderBelongsToUser(Long orderId, Long userId) {
        Order order = orderDAO.findById(orderId).get();
        return order.getCustomer().getUser().getId() ==  userId;
    }
}
