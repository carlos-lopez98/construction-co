package com.solvd.constructionco.util;

import com.solvd.constructionco.models.Equipment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMEquipmentParser implements iParser<Equipment> {
    private Document document;

    public DOMEquipmentParser(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Equipment parse() {
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
