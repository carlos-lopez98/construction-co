package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.models.Task;
import com.solvd.constructionco.service.impl.JSONParseService;
import com.solvd.constructionco.service.mybatisimpl.CustomerService;
import com.solvd.constructionco.service.mybatisimpl.ProjectService;
import com.solvd.constructionco.service.mybatisimpl.TaskService;
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

    //Customer Operations
    public Customer getCustomerById(Integer customerId){
        Customer customer = null;
        CustomerService mybatisCustomerService = (CustomerService) serviceRegistry.getService(com.solvd.constructionco.service.mybatisimpl.CustomerService.class);
        if (mybatisCustomerService != null) {
            //MyBatis
            customer = mybatisCustomerService.getById(customerId);
            return customer;
        }

        CustomerService customerService = (CustomerService) serviceRegistry.getService(com.solvd.constructionco.service.impl.CustomerService.class);
        if (customerService != null) {
            //Regular
           customer =  customerService.getById(customerId);
           return customer;
        }
        return customer;
    }

    //Project Service
    public Project getProjectById(Integer projectId){
        Project project = null;

        ProjectService mybatisCustomerService = (ProjectService) serviceRegistry.getService(com.solvd.constructionco.service.mybatisimpl.ProjectService.class);
        if (mybatisCustomerService != null) {
            //MyBatis
           project =  mybatisCustomerService.getById(projectId);
            return project;
        }

        ProjectService projectService = (ProjectService) serviceRegistry.getService(com.solvd.constructionco.service.impl.ProjectService.class);
        if (projectService != null) {
            //Regular
            project = projectService.getById(projectId);
            return project;
        }

        return project;
    }

    //Task Service
    public Task getTaskById(Integer taskId){

        Task task = null;

        TaskService mybatisTaskService = (TaskService) serviceRegistry.getService(com.solvd.constructionco.service.mybatisimpl.TaskService.class);
        if (mybatisTaskService != null) {
            //MyBatis
            task = mybatisTaskService.getById(taskId);
            return task;
        }

        TaskService taskService = (TaskService) serviceRegistry.getService(com.solvd.constructionco.service.impl.TaskService.class);
        if (taskService != null) {
            //Regular
            task = taskService.getById(taskId);
            return task;
        }
        return task;
    }

}
