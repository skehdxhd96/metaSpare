package com.example.metauniversity.exception;

public class NoSuchSubjectException extends RuntimeException{
    public NoSuchSubjectException(String message) {
        super(message);
    }
}
