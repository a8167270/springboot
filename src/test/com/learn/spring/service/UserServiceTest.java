package com.learn.spring.service;

import com.learn.spring.Application;
import com.learn.spring.dao.UserDao;
import com.learn.spring.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {
    @Mock
    UserDao userDao;

    @InjectMocks
    UserService demoService;

    @Test
    public void testGetDemo() {
        when(userDao.getDemoById(6)).thenReturn(new User("test"));

        User demo = demoService.getUserById(6);
        Assertions.assertThat(demo.getName()).isEqualTo("test");
    }

}