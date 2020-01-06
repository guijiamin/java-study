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

class GreeFridge implements IFridge {
    public void printA() {
        System.out.println("Gree fridge A");
    }

    public void printB() {
        System.out.println("Gree fridge B");
    }
}

class SamsungFridge implements IFridge {
    public void printA() {
        System.out.println("Samsung fridge A");
    }

    public void printB() {
        System.out.println("Samsung fridge B");
    }
}

public abstract class AbstractFactory {
    abstract IFridge produceFridge();
    abstract IAirCondition produceAirCondition();
}

class GreeFactory extends AbstractFactory {
    IFridge produceFridge() {
        return new GreeFridge();
    }

    IAirCondition produceAirCondition() {
        return null;
    }
}

class SamsungFactory extends AbstractFactory {
    IFridge produceFridge() {
        return new SamsungFridge();
    }

    IAirCondition produceAirCondition() {
        return null;
    }
}

