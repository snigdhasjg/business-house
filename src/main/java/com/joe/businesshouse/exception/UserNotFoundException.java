package com.joe.businesshouse.exception;

import com.joe.businesshouse.game.User;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(User user) {
        super(String.format("User %s not found", user));
    }
}
