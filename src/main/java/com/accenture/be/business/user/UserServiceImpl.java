package com.accenture.be.business.user;

import com.accenture.be.access.customer.CustomerDAO;
import com.accenture.be.access.user.UserDAO;
import com.accenture.be.access.user.UserDAOImpl;
import com.accenture.be.business.customer.CustomerService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserDAO userDAO;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        Long userId = userDAO.save(user);
        return userDAO.findById(userId);
    }

    @Override
    public User saveUserWithCustomer(User user, Customer customer) {
        customerService.saveCustomer(customer);
        return this.saveUser(user);
    }

}
