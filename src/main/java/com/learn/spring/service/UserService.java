package com.learn.spring.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.spring.dao.UserDao;
import com.learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(User demo) {
        userDao.insert(demo);
        return 0;
    }

    public User getUserById(long id) {
        return userDao.getDemoById(id);
    }

    public List<User> getAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<User> users = userDao.getAllDemo();
        PageInfo<User> info = new PageInfo<>(users);
        System.out.println(info.getPageNum() + ":" + info.getPages());

        return users;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User demo) {
        userDao.insert(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(long id) {
        userDao.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        int number = userDao.updateUser(user);
        if (number < 1) {
            throw new RuntimeException("update fail!");
        }
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public String getRoleByName(String username) {
        return  userDao.getRoleByName(username);
    }
}
