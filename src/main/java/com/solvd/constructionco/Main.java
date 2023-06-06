package com.solvd.constructionco;

import com.solvd.constructionco.dao.*;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.*;
import com.solvd.constructionco.service.ServiceRegistry;
import com.solvd.constructionco.service.services.*;

public class Main {

    public static void main(String args[]){

        //Initialize all DAOs
        ContractorDAO contractorDAO = new ContractorDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        MaterialDAO materialDAO = new MaterialDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        TaskDAO taskDao = new TaskDAO();

        //Initialize Services
        ConstructionServiceOperations<Contractor, Integer> contractorService = new ContractorService(contractorDAO);
        ConstructionServiceOperations<Customer, Integer> customerService = new CustomerService(customerDAO);
        ConstructionServiceOperations<Employee, Integer> employeeService = new EmployeeService(employeeDAO);
        ConstructionServiceOperations<Equipment, Integer> equipmentService = new EquipmentService(equipmentDAO);
        ConstructionServiceOperations<Invoice, Integer> invoiceService = new InvoiceService(invoiceDAO);
        ConstructionServiceOperations<Material, Integer> materialService = new MaterialService(materialDAO);
        ConstructionServiceOperations<Project, Integer> projectService = new ProjectService(projectDAO);
        ConstructionServiceOperations<Supplier, Integer> supplierService = new SupplierService(supplierDAO);
        ConstructionServiceOperations<Task, Integer> taskService = new TaskService(taskDao);

        //ADD to registry
        ServiceRegistry serviceRegistry = new ServiceRegistry();
        serviceRegistry.registerService(contractorService);
        serviceRegistry.registerService(customerService);
        serviceRegistry.registerService(employeeService);
        serviceRegistry.registerService(equipmentService);
        serviceRegistry.registerService(invoiceService);
        serviceRegistry.registerService(materialService);
        serviceRegistry.registerService(projectService);
        serviceRegistry.registerService(supplierService);
        serviceRegistry.registerService(taskService);



    }
}
