package com.study.designpattern.creational.singleton;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
public class Singleton {
    public static class Lazy {
        private static Singleton instance;

        public static Singleton getInstance() {
            if(instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

    public static class Hungry {
        private static Singleton instance = new Singleton();

        public static Singleton getInstance() {
            return instance;
        }
    }

    public static class DCL {
        private static Singleton instance;

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (DCL.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
