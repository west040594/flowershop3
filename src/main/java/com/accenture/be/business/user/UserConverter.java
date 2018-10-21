package com.accenture.be.business.user;

import com.accenture.be.business.customer.CustomerConverter;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;

public class UserConverter {
    public static UserDTO convertToDTO(User userEntity) {
        UserDTO userDTO = null;
        if(userEntity != null) {
            userDTO = new UserDTO(
                    userEntity.getId(), userEntity.getUsername(),
                    userEntity.getPassword(), userEntity.getEmail(),
                    userEntity.getStatus(), userEntity.getRole(),
                    userEntity.getCreatedAt(), userEntity.getUpdatedAt(),
                    CustomerConverter.convertToDTO(userEntity.getCustomer())
                    );
        }
        return userDTO;
    }
}
