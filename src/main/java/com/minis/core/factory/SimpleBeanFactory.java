package com.minis.core.factory;

import com.minis.core.BeanDefinition;
import com.minis.core.exception.BeansException;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    public SimpleBeanFactory() {}
    @Override
    public Object getBean(String name) throws BeansException {
        Object singleton = this.getSingleton(name);
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(name);
            if (beanDefinition == null) {
                return new BeansException();
            }
            else {
                try {
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.registerSingleton(name, singleton);
            }
        }
        return singleton;
    }

    @Override
    public void registerBean(String name, Object obj) {
        this.registerSingleton(name, obj);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.containsSingleton(name);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(name, beanDefinition);
    }
}
