package com.UKG.IJPmicroservices.Exceptions;

public class CustomUserDetailsException extends RuntimeException{
    public CustomUserDetailsException(String message){
        super(message);
    }
}

