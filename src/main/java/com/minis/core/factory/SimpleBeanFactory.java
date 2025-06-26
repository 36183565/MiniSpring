package com.minis.core.factory;

import com.minis.beans.BeanDefinition;
import com.minis.core.exception.BeansException;
import com.minis.core.registry.BeanDefinitionRegistry;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    public SimpleBeanFactory() {}
    @Override
    public Object getBean(String name) throws BeansException {
        Object singleton = this.getSingleton(name);
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
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
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return beanDefinitionMap.get(name).getClass();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanDefinitionMap.remove(beanName);
        this.beanDefinitionNames.remove(beanName);
        this.removeSingleton(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }
}
