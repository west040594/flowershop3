package com.accenture.fe.rs;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

public interface OrderCgService {
    Response closeOrder(String json);
}
