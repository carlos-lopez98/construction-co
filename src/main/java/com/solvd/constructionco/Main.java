package com.solvd.constructionco;

import com.solvd.constructionco.dao.impl.*;
import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.service.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.*;
import com.solvd.constructionco.service.ConstructionService;
import com.solvd.constructionco.util.ServiceRegistry;
import com.solvd.constructionco.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String INVOICE_XSD_PATH = "src/main/resources/xml/invoice_xsd.xml";
    private static final String EQUIPMENT_XSD_PATH = "src/main/resources/xml/equipment_xsd.xml";
    private static final String CONTRACTOR_XSD_PATH = "src/main/resources/xml/contractor_xsd.xml";
    private static final String INVOICE_FILE_PATH = "src/main/resources/xml/invoice.xml";
    private static final String EQUIPMENT_FILE_PATH = "src/main/resources/xml/equipment.xml";
    private static final String CONTRACTOR_FILE_PATH = "src/main/resources/xml/contractor.xml";
    private static final String INPUT_JAXB = "src/main/resources/xml/input_jaxb.xml";
    private static final String INPUT_JSON = "src/main/resources/json/input_json.json";
    private static final String OUTPUT_JSON = "src/main/resources/json/output_json.json";
    private static final ParseAndValidationService parseAndValidationService = new ParseAndValidationService();



    public static void main(String args[]){

        //Initialize all DAOs
        ConstructionDAO<Contractor, Integer> contractorDAO = new ContractorDAO();
        ConstructionDAO<Customer, Integer> customerDAO = new CustomerDAO();
        ConstructionDAO<Employee, Integer> employeeDAO = new EmployeeDAO();
        ConstructionDAO<Invoice, Integer> invoiceDAO = new InvoiceDAO();
        ConstructionDAO<Project, Integer> projectDAO = new ProjectDAO();
        ConstructionDAO<Supplier, Integer> supplierDAO = new SupplierDAO();
        ConstructionDAO<Task, Integer> taskDao = new TaskDAO();

        //Initialize Services
        JSONParseService jsonParseService = new JSONParseService();


        //ADD to registry
        ServiceRegistry serviceRegistry = new ServiceRegistry();
        serviceRegistry.registerService(jsonParseService);


        ConstructionService constructionService = new ConstructionService(serviceRegistry);


        //Parse to object - DOM
        Contractor contractor = parseAndValidationService.parseContractor(CONTRACTOR_FILE_PATH);
        Invoice invoice = parseAndValidationService.parseInvoice(INVOICE_FILE_PATH);
        Equipment equipment = parseAndValidationService.parseEquipment(EQUIPMENT_FILE_PATH);

        //Validate XML
        if(parseAndValidationService.validateXML(INVOICE_FILE_PATH,INVOICE_XSD_PATH)){
            logger.info("Invoice File Validation was Successful");
        }
        if( parseAndValidationService.validateXML(CONTRACTOR_FILE_PATH,CONTRACTOR_XSD_PATH)){
            logger.info("Contractor File Validation was Successful");
        }
        if( parseAndValidationService.validateXML(EQUIPMENT_FILE_PATH,EQUIPMENT_XSD_PATH)){
            logger.info("Equipment File Validation was Successful");
        }

        //Marhsall
        Invoice invoiceJaxb = parseAndValidationService.unMarshallInvoice(INPUT_JAXB);

        //UnMarshall
        Invoice unMarshalledInvoice = parseAndValidationService.marshallInvoice(invoiceJaxb);

        //Deserialize JSON
        Customer jsonCustomer = constructionService.deserializeCustomerFromJsonFile(INPUT_JSON);
        logger.info("Customer Deserialized " + jsonCustomer.getCustomerName());

        //Serialize JSON
        constructionService.serializeCustomerToJsonFile(jsonCustomer,OUTPUT_JSON);
        logger.info("Customer serialized " + jsonCustomer.getCustomerName());
    }
}
