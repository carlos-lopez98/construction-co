package com.solvd.constructionco.util.parse.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.util.parse.iParser;
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

public class DOMContractorParser implements iParser<Contractor> {

        private static final Logger logger = LogManager.getLogger(Main.class);
        private Document document;

        public DOMContractorParser() {

        }

        @Override
        public String getElementValue(Element parentElement, String elementName) {
            NodeList nodeList = parentElement.getElementsByTagName(elementName);
            Element element = (Element) nodeList.item(0);
            return element.getTextContent();
        }

         @Override
        public Contractor parse(String filePath) {
             try {
                 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                 DocumentBuilder builder = factory.newDocumentBuilder();
                 document = builder.parse(new File(filePath));
             } catch (IOException | SAXException | ParserConfigurationException e) {
                 logger.info("Exception " + e.getClass().getSimpleName() + " was thrown, Details: " + e);
             }

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

