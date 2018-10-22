package com.accenture.be.business.user;

import com.accenture.be.entity.user.User;
import org.springframework.stereotype.Service;

@Service("userLoginService")
public class UserLoginServiceImpl extends UserServiceImpl implements UserLoginService {

    @Override
    public boolean login(String username, String password) {
        User user = super.userDAO.findByUsername(username);

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
