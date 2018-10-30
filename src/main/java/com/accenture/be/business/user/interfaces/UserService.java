package com.accenture.be.business.user.interfaces;

import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.user.exceptions.UserException;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    List<User> findAllUser();
    User saveUser(User user);
    User login(UserDTO userDTO) throws UserException;
    User register(UserDTO userDTO) throws UserException;
    void setUserSession(HttpSession session, UserDTO userDTO);

}
