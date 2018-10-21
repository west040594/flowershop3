package com.accenture.be.business.user;

import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    boolean login(String username, String password);

    boolean checkPassword(User user, String password);



}
