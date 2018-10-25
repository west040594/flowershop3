package com.accenture.be.business.customer.implement;

import com.accenture.be.access.customer.CustomerDAO;
import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    protected CustomerDAO customerDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveCustomer(Customer customer) {
        customerDAO.save(customer);
    }
}
