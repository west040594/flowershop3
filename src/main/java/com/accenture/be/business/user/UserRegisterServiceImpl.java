package com.accenture.be.business.user;

import com.accenture.be.business.customer.CustomerService;
import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;
import com.accenture.fe.enums.user.UserRole;
import com.accenture.fe.enums.user.UserStatus;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


@Service("userRegisterService")
public class UserRegisterServiceImpl extends UserServiceImpl implements UserRegisterService {


    @Override
    public User register(String firstName, String lastName, String username,
                         String email, String password, String passwordConfirm)
    throws ServiceException{

        User user = new User(
                username, password, email,
                UserStatus.ACTIVE, UserRole.USER,
                new Date(), new Date()
        );

        Customer customer = new Customer(
                firstName, lastName, new BigDecimal(2000), 0, user);

        try {
            return this.saveUserWithCustomer(user, customer);
        }
        catch (Exception e) {
            throw  new ServiceException(e.getMessage());
        }

    }


    @Override
    public boolean matchPassword(String password, String passwordConfirm) {
        return false;
    }
}
