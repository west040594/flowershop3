package com.accenture.be.business.cart;

import com.accenture.fe.dto.product.ProductDTO;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Предмет корзины
 * Основные поля:
 * product - Продукт(цветок)
 * quantity - Количество данных продуктов(цветков)
 * discount - скидка если такая имеется
 */
public class CartItem {

    private ProductDTO product;
    private int quantity;
    private int discount;

    public CartItem(ProductDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Возвращает общую стоимость предмета корзины
     * @return Общая стоимость
     */
    public BigDecimal getCartItemTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    /**
     * Возвращает общую стоимость предмета корзины с учетом скидки
     * @return Общая стоимость с скидкой
     */
    public BigDecimal getCartItemTotalDiscount() {
        BigDecimal cartItemTotal = getCartItemTotal();
        if(discount != 0) {
            BigDecimal discountTotal =
                    cartItemTotal.multiply(new BigDecimal(discount)).divide(new BigDecimal(100));
            cartItemTotal = cartItemTotal.subtract(discountTotal);
        }
        return cartItemTotal;
    }

    public String getCartItemTotalRub() {
        Locale loc = new Locale ("ru", "RU");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(loc);
        return formatter.format(getCartItemTotal());
    }

    public String getCartItemTotalDiscountRub() {
        Locale loc = new Locale ("ru", "RU");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(loc);
        return formatter.format(getCartItemTotalDiscount());
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
