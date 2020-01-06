package com.study.designpattern.creational.singleton;

/**
 * Decription
 * <p>
 *     登记式/静态内部类单例模式，能实现和双重校验锁单例模式同样效果
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
public class RegisterSingleton extends Singleton {
    private static class SingletonHolder {
        private final static Singleton instance = new RegisterSingleton();
    }

    private RegisterSingleton() {}

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
