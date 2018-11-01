package com.accenture.be.business.customer.converters;

import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.business.customer.interfaces.CustomerDiscountMarshgallingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;

@Service("customerDiscountMarshgallingService")
public class CustomerDiscountMarshgallingServiceImpl implements CustomerDiscountMarshgallingService {

    @Autowired
    private Unmarshaller unmarshaller;

    @Override
    public CustomerDiscount convertFromXMLToCustomerDiscount(String xmlFile) throws IOException {
        FileInputStream is = null;
        try {
            is = new FileInputStream(xmlFile);
            return (CustomerDiscount) unmarshaller.unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
