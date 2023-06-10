package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.util.parse.impl.DOMInvoiceParser;

public class ParserService {

    private static final String INVOICE_FILE_PATH = "src/main/resources/xml/invoice.xml";
    private static final String CONTRACTOR_FILE_PATH = "src/main/resources/xml/contractor.xml";
    private static final String EQUIPMENT_FILE_PATH = "src/main/resources/xml/equipment.xml";
    private static final DOMInvoiceParser invoiceParser = new DOMInvoiceParser();


    public ParserService(){

    };


    public void parseContractor(String contractorFilePath){




    }


    public void parseEquipment(String equipmentFilePath){


    }


    public void parseInvoice(String invoiceFilePath){

    invoiceParser.parse(INVOICE_FILE_PATH);

    }
}
