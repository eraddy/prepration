package org.example.core;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException()
    {
        super("No such user exist");
    }
}
