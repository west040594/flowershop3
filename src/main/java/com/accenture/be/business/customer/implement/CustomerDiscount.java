package com.accenture.be.business.customer.implement;

public class CustomerDiscount {
    private long customerId;
    private int newDiscount;

    public CustomerDiscount() {
    }

    public CustomerDiscount(long customerId, int newDiscount) {
        this.customerId = customerId;
        this.newDiscount = newDiscount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getNewDiscount() {
        return newDiscount;
    }

    public void setNewDiscount(int newDiscount) {
        this.newDiscount = newDiscount;
    }
}
