package com.study.tinymybatis;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public interface StudentMapper {
    Student findById(int id);

    void insert(Student student);
}
