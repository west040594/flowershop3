package com.accenture.fe.rs;

import com.accenture.be.business.order.interfaces.OrderService;
import com.accenture.fe.dto.order.OrderDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

@Component("orderCgService")
@Path("/order")
public class OrderCgServiceImpl implements OrderCgService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Mapper mapper;

    @Override
    @POST
    @Path("/close")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response closeOrder(String json) {
        Response response = null;
        //Берем данные с ajax запроса(id заказа)
        JsonParser parser = new JsonParser();
        JsonElement jsonTree  = parser.parse(new StringReader(json));
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        Long orderId = jsonObject.get("orderId").getAsLong();
        OrderDTO orderDTO = mapper.map(orderService.changeOrderStatusToClosed(orderId), OrderDTO.class);
        if (orderDTO != null) {
            //Отправляем json с данными корзины
            
            orderDTO.setOrderProducts(null); // TODO: 06.11.2018 КОСТЫЛЬ 
            response = Response.status(Response.Status.OK).entity(orderDTO).build();
        }
        return response;
    }
}
