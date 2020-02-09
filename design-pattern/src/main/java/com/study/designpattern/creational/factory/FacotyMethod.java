package com.study.designpattern.creational.factory;

/**
 * Decription
 * <p>
 *     工厂方法模式：对于每个类的实例化都提供一个
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
interface Animal {
    void print();
}

class Dog implements Animal {
    @Override
    public void print() {
        System.out.println("Dog");
    }
}

class Pig implements Animal {
    @Override
    public void print() {
        System.out.println("Pig");
    }
}

interface IFactory {
    Animal produce();
}

class DogFactory implements IFactory {
    @Override
    public Animal produce() {
        return new Dog();
    }
}

class PigFactory implements IFactory {
    @Override
    public Animal produce() {
        return new Pig();
    }
}
