package com.accenture.be.business.cart;

import com.accenture.fe.dto.product.ProductDTO;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Cart {

    private Map<Long, CartItem> items;
    private BigDecimal total;
    private int discount;
    private int itemCount;

    public Cart() {
        discount = 0;
        items = new HashMap<>();
        total = new BigDecimal(0);
    }

    public Cart(int discount) {
        this();
        this.discount = discount;
    }

    public void addItem(CartItem newItem) {
        //Если предмет содержится в корзине то увеличиваем его количество на 1
        if(items.containsKey(newItem.getProduct().getId())) {
            CartItem item = items.get(newItem.getProduct().getId());
            item.setQuantity(item.getQuantity() + 1);
            newItem.setQuantity(newItem.getQuantity() + 1);
        //Иначае добавляем новый элемент в корзину
        } else {
            items.put(newItem.getProduct().getId(), newItem);
        }
        calculateTotalCost();
        calculateCount();
    }

    public boolean removeItem(CartItem item) {
        if(items.containsKey(item.getProduct().getId())) {
            items.remove(item.getProduct().getId());
            return true;
        }
        return false;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getTotalRub() {
        Locale loc = new Locale ("ru", "RU");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(loc);
        return formatter.format(total);
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Long, CartItem> items) {
        this.items = items;
    }

    private void calculateTotalCost() {
        BigDecimal result = BigDecimal.ZERO;
        for (CartItem cartItem : items.values()) {
            BigDecimal price =  cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();
            BigDecimal priceQuantity = price.multiply(new BigDecimal(quantity));
            result = result.add(priceQuantity);
        }
        BigDecimal discountPrice =
                result.multiply(new BigDecimal(discount)).divide(new BigDecimal(100));
        this.total = result.subtract(discountPrice);
    }

    private void calculateCount() {
        int count = 0;
        for (CartItem cartItem : items.values()) {
            int quantity = cartItem.getQuantity();
            count+=quantity;
        }
        this.itemCount = count;
    }

}
