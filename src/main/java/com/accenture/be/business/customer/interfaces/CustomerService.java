package com.accenture.be.business.customer.interfaces;

import com.accenture.be.business.customer.implement.CustomerDiscount;

/**
 * Сервис для взаимодествия с Покупателями
 */
public interface CustomerService {

    /** Этот метод изменяет у существуюего покупателя его скидку
     * Объект CustomerDiscount состоит из полей - id покупателя и новая скидка
     * @param customerDiscount Объект CustomerDiscount
     */
    void changeCustomerDiscount(CustomerDiscount customerDiscount);
}
