package com.accenture.be.business.order.exceptions;

public class OrderException extends Throwable {

    public static final String ORDER_NOT_FOUND  = "Заказ не найден";

    public OrderException(String message) {
        super(message);
    }

}
