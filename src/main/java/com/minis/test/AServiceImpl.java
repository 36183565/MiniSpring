package com.minis.test;

public class AServiceImpl implements AService {

    private String property1;

    private String name;

    public AServiceImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void sayHello() {
        System.out.println(name + "hello" + property1);
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }
}
