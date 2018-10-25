package com.accenture.be.business.customer.interfaces;

import com.accenture.be.entity.customer.Customer;

import java.math.BigDecimal;

public interface CustomerService {

    void saveCustomer(Customer customer);

    void withdrawFromBalance(BigDecimal withdrawCost, Long customerId);
}
