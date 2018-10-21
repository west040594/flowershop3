package com.accenture.fe.dto.customer;

import java.math.BigDecimal;

public class CustomerDTO {
    private long id;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private int discount;
    private String phoneNumber;
    private String street;
    private String city;
    private String country;

    public CustomerDTO() {
    }

    public CustomerDTO(long id, String firstName, String lastName, BigDecimal balance, int discount, String phoneNumber, String street, String city, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.discount = discount;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
