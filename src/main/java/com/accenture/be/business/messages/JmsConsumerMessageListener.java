package com.accenture.be.business.messages;

import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.business.customer.interfaces.CustomerDiscountMarshgallingService;
import com.accenture.be.business.customer.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

@Component
public class JmsConsumerMessageListener implements MessageListener {

    @Autowired
    private CustomerDiscountMarshgallingService customerDiscountMarshgallingService;

    @Autowired
    private CustomerService customerService;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String customerDiscountXML = textMessage.getText();
            CustomerDiscount customerDiscount =
                    customerDiscountMarshgallingService.convertFromXMLToCustomerDiscount(
                            customerDiscountXML);
            customerService.changeCustomerDiscount(customerDiscount);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }
}
