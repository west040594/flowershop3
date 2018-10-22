package com.accenture.be.business.user.interfaces;

import com.accenture.be.business.user.exceptions.UserRegisterException;
import com.accenture.be.entity.user.User;

public interface UserRegisterService {


    User register(String firstName, String lastName,
                  String username, String email,
                  String password, String passwordConfirm) throws UserRegisterException;



    //boolean matchPassword(String password, String passwordConfirm);


}
