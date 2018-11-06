package com.accenture.be.business.customer.implement;

import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerDAO;

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl() {
        log.info("CustomerServiceActive");
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
            log.debug("Customer with id = {}, changed discount from {} to {}",
                    customer.getId(), customer.getDiscount(), customerDiscount.getNewDiscount());
            customer.setDiscount(customerDiscount.newDiscount);
            customerDAO.save(customer);
        }
    }
}
