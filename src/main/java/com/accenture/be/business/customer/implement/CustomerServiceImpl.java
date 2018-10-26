package com.accenture.be.business.customer.implement;

import com.accenture.be.access.customer.CustomerDAO;
import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    protected CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        System.out.println("customerService ACTIVE");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Customer saveCustomer(Customer customer) {
        Long customerId = customerDAO.save(customer);
        return customerDAO.findById(customerId);
    }

    @Override
    public void withdrawFromBalance(BigDecimal withdrawCost, Long customerId) {
        Customer customer = customerDAO.findById(customerId);
        customer.setBalance(customer.getBalance().subtract(withdrawCost));
        customerDAO.update(customer);
    }
}
