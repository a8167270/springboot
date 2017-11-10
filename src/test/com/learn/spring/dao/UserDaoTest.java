package com.learn.spring.dao;

import com.learn.spring.Application;
import com.learn.spring.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetDemoById() throws Exception {
        userDao.getDemoById(0);
    }

    @Test
    public void getAllDemo() throws Exception {
    }

    @Test
    @Rollback
    public void insert()  {
        User user = new User("testName");
        int number = userDao.insert(user);
        Assertions.assertThat(number).isEqualTo(1);

        User newUser = userDao.getDemoById(user.getId());
        Assertions.assertThat(newUser.getName()).isEqualTo("testName");
    }

    @Test
    public void delete() throws Exception {
        userDao.delete(83);
    }

    @Test
    @Rollback
    public void testUpdateUserSuccess() throws Exception {
        User user = new User("testName");
        userDao.insert(user);

        user.setName("updateTest");
        int number = userDao.updateUser(user);
        Assertions.assertThat(number).isEqualTo(1);
    }

    @Test
    @Rollback
    public void testUpdateUserFail(){
        User user = new User("testName");
        userDao.insert(user);
        userDao.delete(user.getId());

        int number = userDao.updateUser(user);
        Assertions.assertThat(number).isEqualTo(0);
    }
}