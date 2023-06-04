package com.solvd.constructionco.exceptions;

public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException(){};

    public ServiceNotFoundException(String message){
        super(message);
    }
}
