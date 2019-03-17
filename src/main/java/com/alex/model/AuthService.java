package com.alex.model;

import com.alex.dao.DataStorageModel;
import com.alex.model.entity.User;

public class AuthService {
    public static String signIn(String email, String password) {
        User user = DataStorageModel.getDao().getUserByEmail(email);
        if (user != null) {
            boolean isEqual = HashService.compare(password, user.getPassword());
            if (isEqual) {
                return "User checked";
            } else {
                return "Incorrect password";
            }
        }
        return "No such email";
    }

    public static String singUp(String email, String password) {
        User u = DataStorageModel.getDao().getUserByEmail(email);
        if (u != null) {
            return "This e-mail already exist";
        } else {
            User user = new User(email, HashService.getHash(password));
            DataStorageModel.getDao().addUser(user);
            return "User added";
        }
    }
}
