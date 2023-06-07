package com.solvd.constructionco;

import com.solvd.constructionco.dao.impl.*;
import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.*;
import com.solvd.constructionco.service.ConstructionService;
import com.solvd.constructionco.service.ServiceRegistry;
import com.solvd.constructionco.service.services.*;

public class Main {

    public static void main(String args[]){

        //Initialize all DAOs
        ConstructionDAO<Contractor, Integer> contractorDAO = new ContractorDAO();
        ConstructionDAO<Customer, Integer> customerDAO = new CustomerDAO();
        ConstructionDAO<Employee, Integer> employeeDAO = new EmployeeDAO();
        ConstructionDAO<Equipment, Integer> equipmentDAO = new EquipmentDAO();
        ConstructionDAO<Invoice, Integer> invoiceDAO = new InvoiceDAO();
        ConstructionDAO<Material, Integer> materialDAO = new MaterialDAO();
        ConstructionDAO<Project, Integer> projectDAO = new ProjectDAO();
        ConstructionDAO<Supplier, Integer> supplierDAO = new SupplierDAO();
        ConstructionDAO<Task, Integer> taskDao = new TaskDAO();

        //Initialize Services
        ConstructionServiceOperations<Contractor, Integer> contractorService = new ContractorService((ContractorDAO) contractorDAO);
        ConstructionServiceOperations<Customer, Integer> customerService = new CustomerService((CustomerDAO) customerDAO);
        ConstructionServiceOperations<Employee, Integer> employeeService = new EmployeeService((EmployeeDAO) employeeDAO);
        ConstructionServiceOperations<Equipment, Integer> equipmentService = new EquipmentService((EquipmentDAO) equipmentDAO);
        ConstructionServiceOperations<Invoice, Integer> invoiceService = new InvoiceService((InvoiceDAO) invoiceDAO);
        ConstructionServiceOperations<Material, Integer> materialService = new MaterialService((MaterialDAO) materialDAO);
        ConstructionServiceOperations<Project, Integer> projectService = new ProjectService((ProjectDAO) projectDAO);
        ConstructionServiceOperations<Supplier, Integer> supplierService = new SupplierService((SupplierDAO) supplierDAO);
        ConstructionServiceOperations<Task, Integer> taskService = new TaskService((TaskDAO) taskDao);

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

        ConstructionService constructionService = new ConstructionService(serviceRegistry);

    }
}
