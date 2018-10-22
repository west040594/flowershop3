package com.accenture.be.business.user;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;

public interface UserRegisterService {


    User register(String firstName, String lastName,
                  String username, String email,
                  String password, String passwordConfirm);



    boolean matchPassword(String password, String passwordConfirm);

}
