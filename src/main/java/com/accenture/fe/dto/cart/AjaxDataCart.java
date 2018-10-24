package com.accenture.fe.dto.cart;

public class AjaxDataCart {
    private String totalRub;
    private int quantity;

    public AjaxDataCart(String totalRub, int quantity) {
        this.totalRub = totalRub;
        this.quantity = quantity;
    }

    public String getTotalRub() {
        return totalRub;
    }

    public void setTotalRub(String totalRub) {
        this.totalRub = totalRub;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
