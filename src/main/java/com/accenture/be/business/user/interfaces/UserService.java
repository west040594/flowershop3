package com.accenture.be.business.user.interfaces;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    User saveUser(User user);

    User saveUserWithCustomer(User user, Customer customer);

}
