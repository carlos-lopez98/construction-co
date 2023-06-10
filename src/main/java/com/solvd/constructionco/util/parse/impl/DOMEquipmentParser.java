package com.solvd.constructionco.util.parse.impl;

import com.solvd.constructionco.models.Equipment;
import com.solvd.constructionco.util.parse.iParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMEquipmentParser implements iParser<Equipment> {
    private Document document;

    public DOMEquipmentParser() {

    }

    @Override
    public Equipment parse(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
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
