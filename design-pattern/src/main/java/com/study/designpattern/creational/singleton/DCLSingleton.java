package com.study.designpattern.creational.singleton;

/**
 * Decription
 * <p>
 *     双重检查锁单例模式，即Double-Checked-Locks，线程安全，第一次加载时慢
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
public class DCLSingleton extends Singleton {
    private static Singleton instance;

    private DCLSingleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
