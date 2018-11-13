package com.accenture.be.business.messages;

import com.accenture.be.business.customer.exceptions.CustomerException;
import com.accenture.be.business.customer.implement.CustomerDiscount;
import com.accenture.be.business.customer.implement.CustomerServiceImpl;
import com.accenture.be.business.customer.interfaces.CustomerDiscountMarshallingService;
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
    private CustomerDiscountMarshallingService customerDiscountMarshallingService;

    @Autowired
    private CustomerService customerService;

    /** При поступлении нового сообщения из activemq,
     * конвертирует сообещния из xml в объект CustomerDiscount
     * и назначает новую скидку покупателю
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String customerDiscountXML = textMessage.getText();
            CustomerDiscount customerDiscount =
                    customerDiscountMarshallingService.convertFromXMLToCustomerDiscount(
                            customerDiscountXML);
            customerService.changeCustomerDiscount(customerDiscount);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        } catch (CustomerException e) {
            CustomerServiceImpl.log.error(e.getMessage());
        }
    }
}
