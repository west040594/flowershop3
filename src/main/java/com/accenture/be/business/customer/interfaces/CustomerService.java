package com.accenture.be.business.customer.interfaces;

import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.entity.customer.Customer;

import java.math.BigDecimal;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    void changeCustomerDiscount(CustomerDiscount customerDiscount);
    Customer withdrawFromBalance(BigDecimal withdrawCost, Long customerId);
}
