package com.accenture.be.business.user;

import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;

public class UserConverter {
    public static UserDTO convertToDTO(User userEntity) {
        UserDTO userDTO = null;
        if(userEntity != null) {
            userDTO = new UserDTO(
                    userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(),
                    userEntity.getEmail(), userEntity.getFirstName(), userEntity.getLastName(),
                    userEntity.getStatus(), userEntity.getRole(), userEntity.getCreatedAt(),
                    userEntity.getUpdatedAt());
        }
        return userDTO;
    }
}
