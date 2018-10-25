package com.accenture.be.business.customer.converters;

import com.accenture.be.business.user.converters.UserConverter;
import com.accenture.be.entity.customer.Customer;
import com.accenture.fe.dto.customer.CustomerDTO;

public class CustomerConverter {

    public static CustomerDTO convertToDTO(Customer customerEntity) {
        CustomerDTO customerDTO = null;
        if(customerEntity != null) {
            customerDTO = new CustomerDTO(
                    customerEntity.getId(),
                    customerEntity.getFirstName(), customerEntity.getLastName(),
                    customerEntity.getBalance(), customerEntity.getDiscount(),
                    customerEntity.getPhoneNumber(), customerEntity.getStreet(),
                    customerEntity.getCity(), customerEntity.getCountry());
        }
        return customerDTO;
    }

    public static Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customerEntity = null;
        if(customerDTO != null) {
            customerEntity = new Customer(customerDTO.getId(),
                    customerDTO.getFirstName(), customerDTO.getLastName(),
                    customerDTO.getBalance(), customerDTO.getDiscount(),
                    customerDTO.getPhoneNumber(), customerDTO.getStreet(),
                    customerDTO.getCity(), customerDTO.getCountry(),
                    UserConverter.convertToEntity(customerDTO.getUser()));
        }
        return customerEntity;
    }

   /* public static Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customerEntity = null;
        if(customerDTO != null) {
            customerEntity = new Customer(customerDTO.getFirstName(), customerDTO.getLastName(),
                    customerDTO.getBalance(), customerDTO.getDiscount(),
                    UserConverter.convertToEntity(customerDTO.getUser()));
        }
        return customerEntity;
    }*/
}
