package com.accenture.fe.dto.customer;

import com.accenture.be.business.cart.Cart;
import com.accenture.fe.dto.user.UserDTO;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

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
    private Cart cart;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDTO(String firstName, String lastName,
                       BigDecimal balance, int discount) {
        this(firstName, lastName);
        this.balance = balance;
        this.discount = discount;
    }

    public CustomerDTO(long id, String firstName, String lastName, BigDecimal balance,
                       int discount, String phoneNumber, String street, String city, String country) {
        this(firstName, lastName, balance, discount);
        this.id = id;
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

    public String getBalanceRub() {
        Locale loc = new Locale ("ru", "RU");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(loc);
        return formatter.format(balance);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public String getDiscountPercent() {
        return String.valueOf(discount) + "%";
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
