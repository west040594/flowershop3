package com.accenture.be.business.messages;

import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.business.customer.interfaces.CustomerDiscountMarshgallingService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

public class JmsConsumerMessageListener implements MessageListener {

    @Autowired
    private CustomerDiscountMarshgallingService customerDiscountMarshgallingService;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
            CustomerDiscount customerDiscount =
                    customerDiscountMarshgallingService.convertFromXMLToCustomerDiscount(textMessage.getText());
            System.out.println(customerDiscount.getCustomerId());
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }
}
