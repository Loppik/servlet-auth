package com.alex.model;

import com.alex.model.entity.User;

public class AuthService {
    private UserService userService = new UserService();

    public String signIn(String email, String password) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            boolean isEqual = HashService.compare(password, user.getPassword());
            if (isEqual) {
                return JWTProvider.createJWT("1", "issue", email, 10000);
            } else {
                return "Incorrect password";
            }
        }
        return "No such email";
    }

    public String singUp(String email, String password) {
        User u = userService.getUserByEmail(email);
        if (u != null) {
            return "This e-mail already exist";
        } else {
            User user = new User(email, HashService.getHash(password));
            userService.addUser(user);
            return "User added";
        }
    }
}
