package com.accenture.fe.rs;

import javax.ws.rs.core.Response;

/**
 * REST Сервис для работы с Заказами
 */
public interface OrderCgService {
    Response closeOrder(String json);
}
