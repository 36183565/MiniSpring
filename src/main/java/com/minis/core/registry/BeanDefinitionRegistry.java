package com.minis.core.registry;

import com.minis.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    void removeBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);
}
