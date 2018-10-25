package com.accenture.fe.dto.order;

import com.accenture.fe.dto.customer.CustomerDTO;
import com.accenture.fe.dto.orderproduct.OrderProductDTO;
import com.accenture.fe.enums.order.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private long id;
    private BigDecimal total;
    private OrderStatus status;
    private Date createdAt;
    private Date closetAt;
    private CustomerDTO customer;
    private List<OrderProductDTO> orderProducts;

    public OrderDTO() {
    }

    public OrderDTO(long id, CustomerDTO customer, BigDecimal total, OrderStatus status, Date createdAt, Date closetAt) {
        this.id = id;
        this.customer = customer;
        this.total = total;
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

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getClosetAt() {
        return closetAt;
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
}
