package com.solvd.constructionco.util.parse.impl;

import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.util.parse.iParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMContractorParser implements iParser<Contractor> {

        private Document document;

        public DOMContractorParser(String filePath) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getElementValue(Element parentElement, String elementName) {
            NodeList nodeList = parentElement.getElementsByTagName(elementName);
            Element element = (Element) nodeList.item(0);
            return element.getTextContent();
        }

         @Override
        public Contractor parse() {
         Contractor contractor = new Contractor();

            Element contractorElement = document.getDocumentElement();
            contractor.setContractorId(Integer.parseInt(getElementValue(contractorElement, "contractorId")));
            contractor.setContractorName(getElementValue(contractorElement, "contractorName"));
            contractor.setEmail(getElementValue(contractorElement, "email"));
            contractor.setPhoneNumber(getElementValue(contractorElement, "phoneNumber"));
            contractor.setAddress(getElementValue(contractorElement, "address"));

         return contractor;
    }
}

