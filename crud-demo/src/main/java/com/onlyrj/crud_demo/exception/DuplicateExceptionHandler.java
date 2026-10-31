package com.onlyrj.crud_demo.exception;

public class DuplicateExceptionHandler extends RuntimeException{

    public DuplicateExceptionHandler(String message){
        super(message);
    }
}
