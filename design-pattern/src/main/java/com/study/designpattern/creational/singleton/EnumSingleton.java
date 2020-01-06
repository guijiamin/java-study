package com.study.designpattern.creational.singleton;

/**
 * Decription
 * <p>
 *     枚举型单例模式：线程安全，支持自动序列化
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
class Resource {
}

public enum  EnumSingleton {
    INSTANCE;
    private Resource instance;

    EnumSingleton() {
        instance = new Resource();
    }
    //使用方式：EnumSingleton.INSTANCE.getInstance()
    public Resource getInstance() {
        return instance;
    }
}
