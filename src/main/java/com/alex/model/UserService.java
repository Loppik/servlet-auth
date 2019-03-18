package com.alex.model;

import com.alex.dao.IUserDao;
import com.alex.dao.UserDao;
import com.alex.model.entity.User;

public class UserService {
    private IUserDao userDao = new UserDao();

    public void addUser(User user) {
        userDao.add(user);
    }

    public User getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
