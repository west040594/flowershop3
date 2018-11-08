package com.accenture.be.business.user;

import com.accenture.be.business.user.implement.UserServiceImpl;
import com.accenture.be.entity.user.User;
import com.accenture.be.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private List<User> userList = new ArrayList<>();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Before
    public void init(){
        User user1 = new User("User1", "User1Pass", "User1Email");
        user1.setId(1);
        User user2 = new User("User2", "User2Pass", "User2Email");
        user2.setId(2);
        User user3 = new User("User3", "User3Pass", "User3Email");
        user3.setId(3);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById((long) 1)).thenReturn(java.util.Optional.ofNullable(userList.get(0)));
        when(userRepository.findById((long) 2)).thenReturn(java.util.Optional.ofNullable(userList.get(1)));
        when(userRepository.findById((long) 3)).thenReturn(java.util.Optional.ofNullable(userList.get(2)));
        assertEquals(userList.get(0).getId(), userServiceImpl.findUserById(1).getId());
        assertEquals(userList.get(0).getUsername(), userServiceImpl.findUserById(1).getUsername());
        assertEquals(userList.get(1).getId(), userServiceImpl.findUserById(2).getId());
        assertEquals(userList.get(1).getUsername(), userServiceImpl.findUserById(2).getUsername());
        assertEquals(userList.get(2).getId(), userServiceImpl.findUserById(3).getId());
        assertEquals(userList.get(2).getUsername(), userServiceImpl.findUserById(3).getUsername());
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(userList.get(0))).thenReturn(userList.get(0));
        assertEquals(userList.get(0), userServiceImpl.saveUser(userList.get(0)));
        Mockito.verify(userRepository).save(userList.get(0));
    }

    @Test
    public void testFindAllUser() {
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userList, userServiceImpl.findAllUser());
    }

    @Test
    public void testLogin() {

    }

    @Test
    public void testRegister() {

    }
}
