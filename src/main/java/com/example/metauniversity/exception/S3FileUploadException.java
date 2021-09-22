package com.example.metauniversity.exception;

public class S3FileUploadException extends RuntimeException{
    public S3FileUploadException(String message) {
        super(message);
    }
}
