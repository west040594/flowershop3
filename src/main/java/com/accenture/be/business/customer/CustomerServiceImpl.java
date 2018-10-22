package com.accenture.be.business.customer;

import com.accenture.be.access.customer.CustomerDAO;
import com.accenture.be.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    protected CustomerDAO customerDAO;

    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        customerDAO.save(customer);
    }
}
