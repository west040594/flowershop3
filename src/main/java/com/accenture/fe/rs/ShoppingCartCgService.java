package com.accenture.fe.rs;

import com.accenture.be.business.cart.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * REST Сервис для работы с Корзиной Покупателя
 */
public interface ShoppingCartCgService {
    /**
     * Получить все предметы корзины
     * @param request Объект request для получения сессии покупателя и взятия из нее корзины
     * @return Объект Cart представленный форматом json
     */
    Cart getAll(HttpServletRequest request);

    /**
     * Добавить предмет в корзину
     * @param json Json, который содержит id Продукта
     * @param request Объект request для получения сессии покупателя и взятия из нее корзины
     * @return Объект Cart представленный форматом json
     */
    Response addToCart(String json, HttpServletRequest request);

    /**
     * Удалить предмет из корзины
     * @param json Json, который содержит id Продукта
     * @param request Объект request для получения сессии покупателя и взятия из нее корзины
     * @return Объект Cart представленный форматом json
     */
    Response deleteFromCart(String json, HttpServletRequest request);

    /**
     * Удалит все предметы из корзины
     * @param request Объект request для получения сессии покупателя и взятия из нее корзины
     * @return Объект Cart представленный форматом json
     */
    Response deleteAllFromCart(HttpServletRequest request);
}
