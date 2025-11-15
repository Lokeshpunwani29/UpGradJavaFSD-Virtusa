package com.java.dailyPractice.exception;

public class invalidEmail extends RuntimeException {
    public invalidEmail(String message) {
        super(message);
    }
}
