package com.solvd.constructionco.util.parse;

import org.w3c.dom.Element;

public interface IParser<T> {
    T parse(String filePath);
    String getElementValue(Element parentElement, String elementName);
}
