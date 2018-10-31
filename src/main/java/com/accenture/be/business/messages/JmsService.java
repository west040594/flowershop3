package com.accenture.be.business.messages;

public interface JmsService {
    void initialize();
    void destroy();
    void sentMessage(String text);
}
