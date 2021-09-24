package com.example.metauniversity.exception;

public class NoSuchTimeTableException extends RuntimeException{
    public NoSuchTimeTableException(String message) {
        super(message);
    }
}
