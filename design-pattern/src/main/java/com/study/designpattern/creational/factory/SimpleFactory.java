package com.study.designpattern.creational.factory;

/**
 * Decription
 * <p>
 * 简单工厂模式：建立一个工厂，对实现了同一接口的一些类进行实例创建
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
interface Fruit {
    void print();
}

class Apple implements Fruit {
    public void print() {
        System.out.println("Apple");
    }
}

class Orange implements Fruit {
    public void print() {
        System.out.println("Orange");
    }
}

public class SimpleFactory {
    public Fruit produce(String type) {
        if (type.equals("apple")) {
            return new Apple();
        } else if (type.equals("orange")) {
            return new Orange();
        } else {
            System.out.println("没有该类型！");
            return null;
        }
    }
}
