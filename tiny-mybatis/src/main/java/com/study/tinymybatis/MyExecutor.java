package com.study.tinymybatis;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public interface MyExecutor {
    <T> T query(String statement);
}
