package com.Courseware.cutm.ExceptionHandling;

public class ApiException extends RuntimeException {
    public ApiException(String message){
        super(message);
    }
}
