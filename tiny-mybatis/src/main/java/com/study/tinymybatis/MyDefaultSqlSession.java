package com.study.tinymybatis;

import java.lang.reflect.Proxy;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public class MyDefaultSqlSession implements MySqlSession {
    private MyExecutor executor = new MyDefaultExecutor();

    @Override
    public <T> T selectOne(String sql) {
        return executor.query(sql);
    }

    @Override
    public <T> T getMapper(Class<T> interfaces) {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[] {interfaces}, new MyMapperProxy(this));
    }
}
