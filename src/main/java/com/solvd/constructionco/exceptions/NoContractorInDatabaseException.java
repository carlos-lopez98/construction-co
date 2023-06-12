package com.solvd.constructionco.exceptions;

public class NoContractorInDatabaseException extends RuntimeException {
    public NoContractorInDatabaseException(){

    };

    public NoContractorInDatabaseException(String message){
        super(message);
    };
}
