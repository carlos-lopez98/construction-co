package com.solvd.constructionco.util.parse.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Invoice;
import com.solvd.constructionco.util.parse.IParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

public class DOMInvoiceParser implements IParser<Invoice> {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private Document document;

    public DOMInvoiceParser() {

    }

    @Override
    public Invoice parse(String filePath) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        } catch (IOException | SAXException | ParserConfigurationException e) {
          logger.info("Exception " + e.getClass().getSimpleName() + " was thrown, Details: " + e);
        }

        Invoice invoice = new Invoice();

        Element invoiceElement = document.getDocumentElement();
        invoice.setInvoiceId(Integer.parseInt(getElementValue(invoiceElement, "invoiceId")));
        invoice.setDueDate(Date.valueOf(getElementValue(invoiceElement, "dueDate")));
        invoice.setTotalDue(Integer.parseInt(getElementValue(invoiceElement, "totalDue")));

        return invoice;
    }

    @Override
    public String getElementValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        Element element = (Element) nodeList.item(0);
        return element.getTextContent();
    }
}
