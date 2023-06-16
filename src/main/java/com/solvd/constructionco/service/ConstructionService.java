package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.service.impl.JSONParseService;
import com.solvd.constructionco.util.ServiceRegistry;

import java.io.File;
import java.io.IOException;

public class ConstructionService {

    private ServiceRegistry serviceRegistry;



    public ConstructionService(ServiceRegistry serviceRegistry){
        this.serviceRegistry = serviceRegistry;
    }

    public void serializeCustomerToJsonFile(Customer customer, String writeFilePath) {
     JSONParseService jsonParseService = (JSONParseService) serviceRegistry.getService(JSONParseService.class);
     jsonParseService.serializeCustomerToJsonFile(customer, writeFilePath);

    }

    public Customer deserializeCustomerFromJsonFile(String readFilePath ) {
        JSONParseService jsonParseService = (JSONParseService) serviceRegistry.getService(JSONParseService.class);
       Customer customer = jsonParseService.deserializeCustomerFromJsonFile(readFilePath);
       return customer;
    }
}
