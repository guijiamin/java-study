package com.study.tinymybatis;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public interface MySqlSession {
    <T> T selectOne(String var1);

    <T> T getMapper(Class<T> var1);
}
