package com.solvd.constructionco.util.parse;

import org.w3c.dom.Element;

public interface iParser<T> {
    T parse();
    String getElementValue(Element parentElement, String elementName);
}
