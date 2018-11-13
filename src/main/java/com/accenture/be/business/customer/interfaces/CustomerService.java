package com.accenture.be.business.customer.interfaces;

import com.accenture.be.business.customer.exceptions.CustomerException;
import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.entity.customer.Customer;

/**
 * Сервис для взаимодествия с Покупателями
 */
public interface CustomerService {


    /**
     * Этот метод найдет покупателя, которому соотвествует указанный Id
     * @param customerId Id Покупателя
     * @return Объект Покупателя
     */
    Customer findCustomerById(long customerId);

    /** Этот метод изменяет у существуюего покупателя его скидку
     * Объект CustomerDiscount состоит из полей - id покупателя и новая скидка
     * @param customerDiscount Объект CustomerDiscount
     */
    void changeCustomerDiscount(CustomerDiscount customerDiscount) throws CustomerException;
}
