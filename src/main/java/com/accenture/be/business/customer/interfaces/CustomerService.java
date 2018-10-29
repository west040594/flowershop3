package com.accenture.be.business.customer.interfaces;

import com.accenture.be.entity.customer.Customer;

import java.math.BigDecimal;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer withdrawFromBalance(BigDecimal withdrawCost, Long customerId);
}
