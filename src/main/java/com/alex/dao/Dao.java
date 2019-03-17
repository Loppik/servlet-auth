package com.alex.dao;

import com.alex.model.entity.User;

public abstract class Dao {
    public abstract User getUserByEmail(String email);
    public abstract void addUser(User user);
}
