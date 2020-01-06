package com.study.designpattern.creational.singleton;

/**
 * Decription
 * <p>
 *     饿汉式单例模式，线程安全
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
public class HungrySingleton extends Singleton {
    //会在类加载的初始化阶段生成实例
    private static Singleton instance = new HungrySingleton();

    private HungrySingleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
