package com.accenture.fe.rs;


import com.accenture.be.business.cart.Cart;
import com.accenture.be.business.cart.CartItem;
import com.accenture.be.business.product.converters.ProductConverter;
import com.accenture.be.business.product.interfaces.ProductService;
import com.accenture.fe.dto.cart.AjaxDataCart;
import com.accenture.fe.dto.product.ProductDTO;
import com.accenture.fe.dto.user.UserDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

@Component("shoppingCartCgService")
@Path("/shoppingcart")
public class ShoppingCartCgServiceImpl implements ShoppingCartCgService {

    @Autowired
    private ProductService productService;

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/items")
    public Cart getAll(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            Cart cart = (Cart)((UserDTO)session.getAttribute("user")).getCustomer().getCart();
            return cart;
        }
        return null;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addToCart(String json, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Response response = null;
        if(session.getAttribute("user") != null) {
            //Берем данные с ajax запроса(id продукта)
            JsonParser parser = new JsonParser();
            JsonElement jsonTree  = parser.parse(new StringReader(json));
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            Long productId = jsonObject.get("productId").getAsLong();
            //Добавляем в карзину новый продукт
            Cart cart = (Cart)((UserDTO)session.getAttribute("user")).getCustomer().getCart();
            ProductDTO productDTO = ProductConverter.convertToDTO(productService.getProductById(productId));
            CartItem cartItem = new CartItem(productDTO, 1);
            cart.addItem(cartItem);
            //Отправляем json с данными корзины
            response = Response.status(Response.Status.OK).entity(cart).build();
        }
        return response;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/remove")
    public Response deleteFromCart(String json, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Response response = null;
        if(session.getAttribute("user") != null) {
            //Берем данные с ajax запроса(id продукта)
            JsonParser parser = new JsonParser();
            JsonElement jsonTree  = parser.parse(new StringReader(json));
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            Long productId = jsonObject.get("productId").getAsLong();
            //Удаляем из корзины еденицу товара
            Cart cart = (Cart)((UserDTO)session.getAttribute("user")).getCustomer().getCart();
            ProductDTO productDTO = ProductConverter.convertToDTO(productService.getProductById(productId));
            CartItem cartItem = new CartItem(productDTO, 1);
            cart.removeItem(cartItem);
            //Отправляем json с данными корзины
            response = Response.status(Response.Status.OK).entity(cart).build();
        }
        return response;
    }

    @GET
    @Path("/removeall")
    @Override
    public Response deleteAllFromCart(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Response response = null;
        if(session.getAttribute("user") != null) {
            Cart cart = (Cart)((UserDTO)session.getAttribute("user")).getCustomer().getCart();
            cart.removeAllItem();
            //Отправляем json с данными корзины
            AjaxDataCart ajaxDataCart = new AjaxDataCart(cart.getTotalRub(), cart.getItemCount());
            response = Response.status(Response.Status.OK).entity(cart).build();
        }
        return response;
    }
}
