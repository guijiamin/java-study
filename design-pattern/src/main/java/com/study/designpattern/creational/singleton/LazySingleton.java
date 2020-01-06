package com.study.designpattern.creational.singleton;

/**
 * Decription
 * <p>
 *     懒汉单例模式
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
public class LazySingleton extends Singleton {
    private static Singleton instance;

    private LazySingleton() {}

    public static Singleton getInstance() {//非线程安全
//    public static synchronized LazySingleton getInstance() {//线程安全，加锁会影响效率
        if (instance == null) {
             instance = new LazySingleton();
        }
        return instance;
    }
}
