package com.solvd.constructionco.util.parse.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Equipment;
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

public class DOMEquipmentParser implements iParser<Equipment> {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private Document document;

    public DOMEquipmentParser() {

    }

    @Override
    public Equipment parse(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        }  catch (IOException | SAXException | ParserConfigurationException e) {
            logger.info("Exception " + e.getClass().getSimpleName() + " was thrown, Details: " + e);
        }

        Equipment equipment = new Equipment();

        Element equipmentElement = document.getDocumentElement();
        equipment.setEquipmentId(Integer.parseInt(getElementValue(equipmentElement, "equipmentId")));
        equipment.setEquipmentName(getElementValue(equipmentElement, "equipmentName"));
        equipment.setDescription(getElementValue(equipmentElement, "description"));
        equipment.setAvailable(Boolean.parseBoolean(getElementValue(equipmentElement, "available")));

        return equipment;
    }

    @Override
    public String getElementValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        Element element = (Element) nodeList.item(0);
        return element.getTextContent();
    }
}
