package com.study.tinymybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public class MyMapperProxy implements InvocationHandler {
    private MySqlSession sqlSession;

    public MyMapperProxy() {}

    public MyMapperProxy(MySqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Exception {
        String mapperClass = method.getDeclaringClass().getName();
        if (StudentMapperXML.namespace.equals(mapperClass)) {
            String methodName = method.getName();
            String methodSql = StudentMapperXML.getMethodSql(methodName);
            String sql = String.format(methodSql, String.valueOf(args[0]));
            return sqlSession.selectOne(sql);
        }
        return null;
    }
}
