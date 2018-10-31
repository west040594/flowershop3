package com.accenture.be.business.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

@Service("jmsService")
public class JmsServiceImpl implements JmsService {

    @Autowired
    private Queue queue;

    @Autowired
    private ConnectionFactory connectionFactory;

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    @Override
    @PostConstruct
    public void initialize()
    {
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    @PreDestroy
    public void destroy()
    {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sentMessage(String text) {
        try {
            Message message = session.createTextMessage(text);
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
