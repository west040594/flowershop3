package com.accenture.be.business.customer;

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
                    customerEntity.getCity(), customerEntity.getCountry()
                    );
        }
        return customerDTO;
    }
}
