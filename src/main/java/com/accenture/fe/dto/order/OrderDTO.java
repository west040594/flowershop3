package com.accenture.fe.dto.order;

import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;
import com.accenture.fe.dto.product.ProductDTO;
import com.accenture.fe.enums.order.OrderStatus;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderDTO {

    private long id;
    private BigDecimal total;
    private String deliveryAddress;
    private OrderStatus status;
    private Date createdAt;
    private Date closetAt;
    private CustomerDTO customer;
    private List<OrderProductDTO> orderProducts;
    private List<ProductDTO> products;

    public OrderDTO() {
    }

    public OrderDTO(long id, CustomerDTO customer, BigDecimal total, String deliveryAddress,
                    OrderStatus status, Date createdAt, Date closetAt) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.createdAt = createdAt;
        this.closetAt = closetAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getTotalRub() {
        Locale loc = new Locale ("ru", "RU");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(loc);
        return formatter.format(total);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtFormat() {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault());
        return newDateFormat.format(createdAt);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosetAt() {
        return closetAt;
    }

    public String getClosetAtFormat() {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault());
        return newDateFormat.format(closetAt);
    }

    public void setClosetAt(Date closetAt) {
        this.closetAt = closetAt;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
