package com.minis.core.factory;

import com.minis.core.exception.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Boolean containsBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
