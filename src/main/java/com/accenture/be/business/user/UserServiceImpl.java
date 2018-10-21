package com.accenture.be.business.user;

import com.accenture.be.access.user.UserDAO;
import com.accenture.be.access.user.UserDAOImpl;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Override
    public boolean login(String username, String password) {
        User user = userDAO.findByUsername(username);

        if(user == null) {
            user = userDAO.findByEmail(username);
        }
        if(user != null) {
            return checkPassword(user, password);
        }
        return false;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
