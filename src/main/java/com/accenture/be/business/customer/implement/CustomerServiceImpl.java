package com.accenture.be.business.customer.implement;

import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerDAO;

    public CustomerServiceImpl() {
    }

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerDAO.save(customer);
    }

    @Transactional
    @Override
    public Customer withdrawFromBalance(BigDecimal withdrawCost, Long customerId) {
        Customer customer = customerDAO.findById(customerId).get();
        customer.setBalance(customer.getBalance().subtract(withdrawCost));
        customerDAO.save(customer);
        return customer;
    }

    @Transactional
    @Override
    public void changeCustomerDiscount(CustomerDiscount customerDiscount) {
        Customer customer = customerDAO.findById(customerDiscount.getCustomerId()).get();
        if(customerDiscount.newDiscount > 0  &&  customerDiscount.newDiscount < 100)
        {
            customer.setDiscount(customerDiscount.newDiscount);
            customerDAO.save(customer);
        }
    }
}
