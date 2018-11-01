package com.accenture.be.business.customer.converters;

import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.business.customer.interfaces.CustomerDiscountMarshgallingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Service("customerDiscountMarshgallingService")
public class CustomerDiscountMarshgallingServiceImpl implements CustomerDiscountMarshgallingService {

 /*   @Autowired
    private Unmarshaller unmarshaller;*/

    @Autowired
    private Marshaller marshaller;

    @Override
    public void convertFromCustomerDiscountToXML(CustomerDiscount customerDiscount, String filepath) throws IOException {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filepath);
            marshaller.marshal(customerDiscount, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    @Override
    public CustomerDiscount convertFromXMLToCustomerDiscount(String xmlFile) throws IOException {
       /* StringReader reader = new StringReader(xmlFile);
        return (CustomerDiscount) unmarshaller.unmarshal(new StreamSource(reader));*/
        JAXBContext jaxbContext = null;
        CustomerDiscount customerDiscount = null;
        try {
            jaxbContext = JAXBContext.newInstance(CustomerDiscount.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlFile);
            customerDiscount = (CustomerDiscount) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  customerDiscount;
    }
}
