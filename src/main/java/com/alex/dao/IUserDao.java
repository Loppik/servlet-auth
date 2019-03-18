package com.alex.dao;

import com.alex.model.entity.User;

public interface IUserDao {
    public User getByEmail(String email);
    public void add(User user);
}
