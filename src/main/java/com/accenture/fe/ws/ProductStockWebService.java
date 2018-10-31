package com.accenture.fe.ws;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public interface ProductStockWebService {
    void increaseProductStockSize(@WebParam(name = "count") int count);
}
