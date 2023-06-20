package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.service.impl.JSONParseService;
import com.solvd.constructionco.service.mybatisimpl.CustomerService;
import com.solvd.constructionco.service.mybatisimpl.ProjectService;
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

    //Customer Service
    public void getCustomerById(Integer customerId){
        CustomerService mybatisCustomerService = (CustomerService) serviceRegistry.getService(com.solvd.constructionco.service.mybatisimpl.CustomerService.class);
        if (mybatisCustomerService != null) {
            // Perform operations using the MyBatis implementation
            mybatisCustomerService.getById(customerId);
        }

        CustomerService otherCustomerService = (CustomerService) serviceRegistry.getService(com.solvd.constructionco.service.impl.CustomerService.class);
        if (otherCustomerService != null) {
            // Perform operations using the other implementation
            otherCustomerService.getById(customerId);
        }

    }

    //Project Service
    public void getProjectById(Integer projectId){

        ProjectService mybatisCustomerService = (ProjectService) serviceRegistry.getService(com.solvd.constructionco.service.mybatisimpl.ProjectService.class);
        if (mybatisCustomerService != null) {
            //MyBatis
            mybatisCustomerService.getById(projectId);
        }

        ProjectService projectService = (ProjectService) serviceRegistry.getService(com.solvd.constructionco.service.impl.ProjectService.class);
        if (projectService != null) {
            //Regular
            projectService.getById(projectId);
        }

    }



}
