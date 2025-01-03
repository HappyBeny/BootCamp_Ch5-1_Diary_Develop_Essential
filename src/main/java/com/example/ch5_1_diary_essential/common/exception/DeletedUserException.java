package com.example.ch5_1_diary_essential.common.exception;

public class DeletedUserException extends RuntimeException{
    public DeletedUserException(String message) { super(message); }
}
