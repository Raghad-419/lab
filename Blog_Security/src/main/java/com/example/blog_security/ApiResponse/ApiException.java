package com.example.blog_security.ApiResponse;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
