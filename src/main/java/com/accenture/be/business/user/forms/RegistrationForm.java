package com.accenture.be.business.forms;

import com.accenture.be.entity.customer.Customer;
import com.accenture.be.entity.user.User;

public class RegistrationForm {

    private User user;
    private Customer customer;
    private String confirmPassword;

    public RegistrationForm(User user, Customer customer, String confirmPassword) {
        this.user = user;
        this.customer = customer;
        this.confirmPassword = confirmPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
