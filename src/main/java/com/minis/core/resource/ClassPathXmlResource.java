package com.minis.core.resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

public class ClassPathXmlResource implements Resource {

    private Document document;

    private Element rootElement;

    private Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(fileName);
        try {
            this.document = saxReader.read(url);
            this.rootElement = document.getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean hasNext() {
        return elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return elementIterator.next();
    }
}
