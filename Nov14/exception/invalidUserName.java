package com.java.dailyPractice.exception;

public class invalidUserName extends RuntimeException {
    public invalidUserName(String message) {
        super(message);
    }
}
