package com.minis.core.factory;

import com.minis.core.exception.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    void registerBean(String name, Object obj);

    Boolean containsBean(String name);
}
