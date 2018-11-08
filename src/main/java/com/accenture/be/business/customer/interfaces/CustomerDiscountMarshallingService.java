package com.accenture.be.business.customer.interfaces;

import com.accenture.be.business.customer.implement.CustomerDiscount;
import java.io.IOException;

/**
 * Сервис для конвертирования объекта CustomerDiscount в xml и обратно
 */
public interface CustomerDiscountMarshallingService {

    /**
     * Этот метод конвертирует объект СustomerDiscount в xml и сохраняет его
     * @param customerDiscount Объект СustomerDiscount
     * @param filepath Путь для сохрания xml файла
     * @throws IOException
     */
    void convertFromCustomerDiscountToXML(CustomerDiscount customerDiscount, String filepath)
            throws IOException;

    /**
     * Этот метод конвертирует xml в объект СustomerDiscount и возвращает его
     * @param xmlFile xml файл
     * @return Объект СustomerDiscount
     * @throws IOException
     */
    CustomerDiscount convertFromXMLToCustomerDiscount(String xmlFile) throws IOException;
}
