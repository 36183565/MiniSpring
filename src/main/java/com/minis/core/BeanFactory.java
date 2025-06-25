package com.minis.core;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    void registerBean(String name, BeanDefinition beanDefinition);
}
