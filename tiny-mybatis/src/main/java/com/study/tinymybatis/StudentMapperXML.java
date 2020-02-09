package com.study.tinymybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public class StudentMapperXML {
    public static final String namespace = "com.study.tinymybatis.StudentMapper";

    private static Map<String, String> methodSqlMap = new HashMap<>();

    static {
        methodSqlMap.put("findById", "select * from student where id = %s");
    }

    public static String getMethodSql(String method) {
        return methodSqlMap.get(method);
    }
}
