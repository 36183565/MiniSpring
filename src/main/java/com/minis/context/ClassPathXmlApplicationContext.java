package com.minis.context;

import com.minis.core.*;
import com.minis.core.factory.SimpleBeanFactory;

public class ClassPathXmlApplicationContext implements BeanFactory {

    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        this.beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this.beanFactory);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return this.beanFactory.getBean(name);
    }

    @Override
    public void registerBean(String name, BeanDefinition beanDefinition) {
        this.beanFactory.registerBean(name, beanDefinition);
    }
}
