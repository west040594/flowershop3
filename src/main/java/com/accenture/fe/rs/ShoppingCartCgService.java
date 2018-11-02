package com.accenture.fe.rs;

import com.accenture.be.business.cart.Cart;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

public interface ShoppingCartCgService {
    Cart getAll(HttpServletRequest request);
    Response addToCart(String json, HttpServletRequest request);
    Response deleteFromCart(String json, HttpServletRequest request);
    Response deleteAllFromCart(HttpServletRequest request);
}
