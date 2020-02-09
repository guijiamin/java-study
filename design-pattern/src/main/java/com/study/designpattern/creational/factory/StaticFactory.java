package com.study.designpattern.creational.factory;

/**
 * Decription
 * <p>
 *     静态工厂方法：对每个类的实例创建提供一个静态方法
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
interface Teacher {
    void print();
}

class TeacherA implements Teacher {
    @Override
    public void print() {
        System.out.println("TeacherA");
    }
}

class TeacherB implements Teacher {
    @Override
    public void print() {
        System.out.println("TeacherB");
    }
}

public class StaticFactory {
    public static Teacher getA() {
        return new TeacherA();
    }

    public static Teacher getB() {
        return new TeacherB();
    }
}
