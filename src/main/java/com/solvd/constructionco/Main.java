package com.solvd.constructionco;

import com.solvd.constructionco.dao.impl.*;
import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.*;
import com.solvd.constructionco.service.ConstructionService;
import com.solvd.constructionco.util.ServiceRegistry;
import com.solvd.constructionco.service.impl.*;
import com.solvd.constructionco.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String invoiceFilePath = "src/main/resources/xml/invoice.xml";
    private static final String invoiceXSDFilePath = "src/main/resources/xml/invoice_xsd.xml";
    private static final String equipmentFilePath = "src/main/resources/xml/equipment.xml";
    private static final String equipmentXSDFilePath = "src/main/resources/xml/equipment_xsd.xml";
    private static final String contractorFilePath = "src/main/resources/xml/contractor.xml";
    private static final String contractorXSDFilePath = "src/main/resources/xml/contractor_xsd.xml";

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

        iParser<Equipment> equipmentParser = new DOMEquipmentParser(equipmentFilePath);
        iParser<Invoice> invoiceParser = new DOMInvoiceParser(invoiceFilePath);
        iParser<Contractor> contractorParser = new DOMContractorParser(contractorFilePath);
        Contractor contractor = (Contractor) contractorParser.parse();
        Equipment equipment = (Equipment) equipmentParser.parse();
        Invoice invoice = (Invoice) invoiceParser.parse();

        // Access and use the parsed invoice object
        logger.info((("Invoice object succesfully created, Invoice: ID " + invoice.getInvoiceId())));
        logger.info((("Equipment object succesfully created, Equipment Name: " + equipment.getEquipmentName())));
        logger.info((("Contractor object succesfully created, Contractor Name: " + contractor.getContractorName())));

        //Checking Validation of XML to Schema
        validateXML(invoiceFilePath, invoiceXSDFilePath);
        validateXML(equipmentFilePath, equipmentXSDFilePath);
        validateXML(contractorFilePath, contractorXSDFilePath);
    }


    //Uses the validator to check validation
    private static void validateXML(String XML, String XSD) {
        DOMValidator validator = new DOMValidator();
        boolean isValid = validator.validateXML(XML, XSD);
        if (isValid) {
            logger.info("XML is valid.");
        } else {
            logger.info("XML is invalid.");
        }
    }
}
