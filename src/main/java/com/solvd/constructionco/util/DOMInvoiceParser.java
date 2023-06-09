package com.solvd.constructionco.util;

import com.solvd.constructionco.models.Invoice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;

public class DOMInvoiceParser implements iParser<Invoice>{

    private Document document;

    public DOMInvoiceParser(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Invoice parse() {
        Invoice invoice = new Invoice();

        Element invoiceElement = document.getDocumentElement();
        invoice.setInvoiceId(Integer.parseInt(getElementValue(invoiceElement, "invoiceId")));
        invoice.setPurchaseOrderId(Integer.parseInt(getElementValue(invoiceElement, "purchaseOrderId")));
        invoice.setCustomerId(Integer.parseInt(getElementValue(invoiceElement, "customerId")));
        invoice.setContractorId(Integer.parseInt(getElementValue(invoiceElement, "contractorId")));
        invoice.setDueDate(LocalDate.parse(getElementValue(invoiceElement, "dueDate")));
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
