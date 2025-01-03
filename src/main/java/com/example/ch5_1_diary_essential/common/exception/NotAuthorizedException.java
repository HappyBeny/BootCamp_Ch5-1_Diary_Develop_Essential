package com.example.ch5_1_diary_essential.common.exception;


public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}