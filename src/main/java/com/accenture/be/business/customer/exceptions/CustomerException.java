package com.accenture.be.business.customer.exceptions;

public class CustomerException extends Throwable {

    public static final String CUSTOMER_NOT_FOUND = "Покупатель не найден";

    public CustomerException(String message) {
        super(message);
    }
}
