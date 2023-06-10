package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.util.parse.impl.DOMContractorParser;
import com.solvd.constructionco.util.parse.impl.DOMEquipmentParser;
import com.solvd.constructionco.util.parse.impl.DOMInvoiceParser;

public class ParserService {


    private static final DOMInvoiceParser invoiceParser = new DOMInvoiceParser();
    private static final DOMContractorParser contractorParser = new DOMContractorParser();
    private static final DOMEquipmentParser equipmentParser = new DOMEquipmentParser();

    public ParserService(){

    };


    public void parseContractor(String contractorFilePath){


        contractorParser.parse(contractorFilePath);

    }


    public void parseEquipment(String equipmentFilePath){

        equipmentParser.parse(equipmentFilePath);
    }


    public void parseInvoice(String invoiceFilePath){

    invoiceParser.parse(invoiceFilePath);

    }
}
