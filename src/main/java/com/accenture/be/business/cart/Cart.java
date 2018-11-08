package com.accenture.be.business.cart;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * Корзина для покупки цветов
 * Основные поля:
 * items - Коллекция предметов в корзине
 * total - Общая стоимость корзины
 * discount - Скидка, если таковая имеется
 * itemCount - Количество продуктов в корзине
 */
public class Cart {

    private Map<Long, CartItem> items;
    private BigDecimal total;
    private int discount;
    private int itemCount;

    public Cart() {
        itemCount = 0;
        discount = 0;
        items = new HashMap<>();
        total = new BigDecimal(0);
    }

    public Cart(int discount) {
        this();
        this.discount = discount;
    }

    /**
     * Этот метод добавит новый предмет в корзину
     * @param newItem Новый предмет для добавления в корзину
     */
    public void addItem(CartItem newItem) {
        //Если предмет содержится в корзине то увеличиваем его количество на 1
        if(items.containsKey(newItem.getProduct().getId())) {
            CartItem item = items.get(newItem.getProduct().getId());
            item.setQuantity(item.getQuantity() + 1);
        //Иначае добавляем новый элемент в корзину
        } else {
            items.put(newItem.getProduct().getId(), newItem);
            newItem.setDiscount(discount);
        }
        //Подчитываем количество и общую стоимость после изменений в корзине
        calculateTotalCost();
        calculateCount();
    }


    /**
     * Этот метод удалит существующий предмет в корзине
     * @param removeItem Существующий предмет в корзине
     */
    public void removeItem(CartItem removeItem) {
        if(items.containsKey(removeItem.getProduct().getId())) {
            CartItem item = items.get(removeItem.getProduct().getId());
            //Если в корзине количество предметов больше 1 то уменьшаем количество на 1
            //Иначае удаляем предмет из корзины полностью
            if(item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                items.remove(item.getProduct().getId());
            }
            //Подчитываем количество и общую стоимость после изменений в корзине
            calculateTotalCost();
            calculateCount();
        }
    }


    /**
     * Этот метод удалит все предметы из корзины
     */
    public void removeAllItem() {
        items.clear();
        total = new BigDecimal(0);
        itemCount = 0;
    }

    /**
     * Этот метод пересчитывает общую стомость в корзине
     */
    private void calculateTotalCost() {
        BigDecimal result = BigDecimal.ZERO;
        //Проходим по все предметам корзины,
        //умножаем количество предметов на их цену и формируем общую стоимость
        for (CartItem cartItem : items.values()) {
            BigDecimal price =  cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();
            BigDecimal priceQuantity = price.multiply(new BigDecimal(quantity));
            result = result.add(priceQuantity);
        }
        //Если присутствует скидка, то формируем общую стомиость по скидке,
        // иначе общая цена учитывается без скидки
        if(discount != 0) {
            BigDecimal discountPrice =
                    result.multiply(new BigDecimal(discount)).divide(new BigDecimal(100));
            this.total = result.subtract(discountPrice);
        } else {
            this.total = result;
        }
    }

    /**
     * Этот метод пересчитывает общее количество продуктов в корзине
     */
    private void calculateCount() {
        int count = 0;
        for (CartItem cartItem : items.values()) {
            int quantity = cartItem.getQuantity();
            count+=quantity;
        }
        this.itemCount = count;
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

    public List<CartItem> getItemList() {
       return new ArrayList<CartItem>(items.values());
    }

}
