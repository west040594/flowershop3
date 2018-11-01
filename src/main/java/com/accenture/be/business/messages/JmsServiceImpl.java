package com.accenture.be.business.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

@Service("jmsService")
public class JmsServiceImpl implements JmsService{

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private JmsConsumerMessageListener jmsConsumerMessageListener;

    private Queue outQueue;
    private Queue inQueue;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;

    @Override
    @PostConstruct
    public void initialize()
    {
        try {
            outQueue = (Queue)context.getBean("outQueue");
            inQueue = (Queue) context.getBean("inQueue");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(outQueue);
            consumer = session.createConsumer(inQueue);
            consumer.setMessageListener(jmsConsumerMessageListener);
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
