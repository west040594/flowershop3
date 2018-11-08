package com.accenture.be.business.customer.implement;

import com.accenture.be.business.customer.interfaces.CustomerService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerDAO;

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

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
