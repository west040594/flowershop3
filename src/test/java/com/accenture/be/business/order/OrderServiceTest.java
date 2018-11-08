package com.accenture.be.business.order;

import static org.mockito.Mockito.*;
import com.accenture.be.business.order.implement.OrderServiceImpl;
import com.accenture.be.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepositoryMock;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    public OrderServiceTest() {
    }

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {

    }

    @Test
    public void testSave() {

    }
}
