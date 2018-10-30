package com.accenture.be.business.user.interfaces;

import com.accenture.be.entity.user.User;

import java.io.IOException;

public interface UserMarshgallingService{
    void convertFromUserToXML(User user) throws IOException;
    User convertFromXMLToUser(String xmlFile) throws IOException;
}
