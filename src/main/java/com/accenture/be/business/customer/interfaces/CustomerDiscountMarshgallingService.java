package com.accenture.be.business.customer.interfaces;

import com.accenture.be.business.customer.implement.CustomerDiscount;

import java.io.IOException;

public interface CustomerDiscountMarshgallingService {
    CustomerDiscount convertFromXMLToCustomerDiscount(String xmlFile) throws IOException;
}
