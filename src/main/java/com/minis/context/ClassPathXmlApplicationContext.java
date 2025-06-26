package com.minis.context;

import com.minis.core.event.ApplicationEvent;
import com.minis.core.event.ApplicationEventPublisher;
import com.minis.core.exception.BeansException;
import com.minis.core.factory.BeanFactory;
import com.minis.core.factory.SimpleBeanFactory;
import com.minis.core.reader.XmlBeanDefinitionReader;
import com.minis.core.resource.ClassPathXmlResource;
import com.minis.core.resource.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {

    private SimpleBeanFactory beanFactory;

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
    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public void publishEvent(ApplicationEvent event) {}

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public void publishEvent(com.apple.eawt.ApplicationEvent event) {

    }
}
