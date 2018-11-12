package com.accenture.fe.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Web Сервис для работы с продуктами
 */
@WebService
public interface ProductStockWebService {
    /**
     * Увеличивает количество всех продуктов(цветков) в магазине на указаное количество
     * @param count Количество для увелечения
     */
    void increaseProductStockSize(@WebParam(name = "count") int count);
}
