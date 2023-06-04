package com.solvd.constructionco.exceptions;

public class ServiceAlreadyInRegistry extends RuntimeException{

    public ServiceAlreadyInRegistry(){

    };

    public ServiceAlreadyInRegistry(String message){
        super(message);
    };
}
