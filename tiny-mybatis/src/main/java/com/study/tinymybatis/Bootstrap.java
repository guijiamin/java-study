package com.study.tinymybatis;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public class Bootstrap {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MyDefaultSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findById(1);
        System.out.println(student);
    }
}
