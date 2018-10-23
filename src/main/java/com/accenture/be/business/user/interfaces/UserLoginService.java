package com.accenture.be.business.user.interfaces;

import com.accenture.be.entity.user.User;

public interface UserLoginService {

    boolean login(String username, String password);

    boolean checkPassword(User user, String password);
}