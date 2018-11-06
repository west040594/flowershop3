package com.accenture.fe.rs;

import com.accenture.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component("userCgService")
@Path("/user")
public class UserCgServiceImpl implements UserCgService {
    @Autowired
    private UserRepository userDAO;

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/exist/{username}")
    public boolean checkUserExist(@PathParam("username") String username) {
        return (userDAO.findByUsername(username) != null);
    }
}
