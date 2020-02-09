package com.study.designpattern.creational.factory;

/**
 * Decription
 * <p>
 *     抽象工厂模式：一个工厂可以生产很多产品，每个工厂生产的产品是通用的
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
interface IFridge {
    void printA();
    void printB();
}

interface IAirCondition {
    void printA();
    void printB();
}

class GreenFridge implements IFridge {
    @Override
    public void printA() {
        System.out.println("Gree fridge A");
    }
    @Override
    public void printB() {
        System.out.println("Gree fridge B");
    }
}

class SamsungFridge implements IFridge {
    @Override
    public void printA() {
        System.out.println("Samsung fridge A");
    }
    @Override
    public void printB() {
        System.out.println("Samsung fridge B");
    }
}

public abstract class AbstractFactory {
    abstract IFridge produceFridge();
    abstract IAirCondition produceAirCondition();
}

class GreenFactory extends AbstractFactory {
    @Override
    IFridge produceFridge() {
        return new GreenFridge();
    }
    @Override
    IAirCondition produceAirCondition() {
        return null;
    }
}

class SamsungFactory extends AbstractFactory {
    @Override
    IFridge produceFridge() {
        return new SamsungFridge();
    }
    @Override
    IAirCondition produceAirCondition() {
        return null;
    }
}

