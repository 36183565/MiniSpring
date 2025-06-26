package com.minis.core.reader;

import com.minis.beans.BeanDefinition;
import com.minis.core.factory.SimpleBeanFactory;
import com.minis.core.resource.Resource;
import org.dom4j.Element;

public class XmlBeanDefinitionReader {

    private SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);
            this.simpleBeanFactory.registerBeanDefinition(id, beanDefinition);
        }
    }
}
