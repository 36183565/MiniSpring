package com.minis;

import com.minis.beans.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.readXml(fileName);
        this.instanceBeans();
    }

    private void readXml(String fileName) {
        SAXReader reader = new SAXReader();
        try {
            URL url = this.getClass().getClassLoader().getResource(fileName);
            Document document = reader.read(url);
            Element root = document.getRootElement();
            for (Element element : (List<Element>) root.elements()) {
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(id, className);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void instanceBeans() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            singletons.put(beanDefinition.getId(),
                    Class.forName(beanDefinition.getClassName()).newInstance());
        }
    }

    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

}
