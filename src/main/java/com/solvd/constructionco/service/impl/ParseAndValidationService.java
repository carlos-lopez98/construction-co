package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.models.Equipment;
import com.solvd.constructionco.models.Invoice;
import com.solvd.constructionco.util.DOMValidator;
import com.solvd.constructionco.util.parse.impl.DOMContractorParser;
import com.solvd.constructionco.util.parse.impl.DOMEquipmentParser;
import com.solvd.constructionco.util.parse.impl.DOMInvoiceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseAndValidationService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final DOMInvoiceParser invoiceParser = new DOMInvoiceParser();
    private static final DOMContractorParser contractorParser = new DOMContractorParser();
    private static final DOMEquipmentParser equipmentParser = new DOMEquipmentParser();

    public ParseAndValidationService(){
    };

    public Contractor parseContractor(String contractorFilePath){
        return contractorParser.parse(contractorFilePath);
    }

    public Equipment parseEquipment(String equipmentFilePath){
      return equipmentParser.parse(equipmentFilePath);
    }

    public Invoice parseInvoice(String invoiceFilePath){
    return invoiceParser.parse(invoiceFilePath);
    }


    public boolean validateXML(String XMLFilePath, String XSDFilePath) {
        DOMValidator validator = new DOMValidator();
        boolean isValid = validator.validateXML(XMLFilePath, XSDFilePath);
        if (isValid) {
            logger.info("XML is valid.");
            return isValid;
        } else {
            logger.info("XML is invalid.");
            return false;
        }
    }
}
