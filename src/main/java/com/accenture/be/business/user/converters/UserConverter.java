package com.accenture.be.business.user.converters;

import com.accenture.be.business.customer.converters.CustomerConverter;
import com.accenture.be.entity.user.User;
import com.accenture.fe.dto.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

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

    public static List<UserDTO> convertToDTO(List<User> userEnities) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userEnities) {
            UserDTO userDTO = UserConverter.convertToDTO(user);
            if(userDTO != null) {
                userDTOS.add(userDTO);
            }
        }
        return userDTOS;
    }

    public static User convertToEntity(UserDTO userDTO) {
        User userEntity = null;
        if(userDTO != null) {
            userEntity = new User(
                    userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(),
                    userDTO.getStatus(), userDTO.getRole(), userDTO.getCreatedAt(), userDTO.getUpdatedAt(),
                    CustomerConverter.convertToEntity(userDTO.getCustomer()));
        }
        return userEntity;
    }

}
