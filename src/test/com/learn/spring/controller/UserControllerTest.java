package com.learn.spring.controller;

import com.learn.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {
    @Test
    public void getUser1() throws Exception {
    }

    @Test
    public void getUsers() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void getUser() throws Exception {
    }

}