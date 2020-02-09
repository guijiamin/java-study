package com.study.tinymybatis;

import java.sql.*;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-13.
 *
 * @author guijiamin.
 */
public class MyDefaultExecutor implements MyExecutor {
    @Override
    public <T> T query(String statement) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("", "", "");
            String sql = statement;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            Student student = null;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setSex(resultSet.getInt("sex"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setAddress(resultSet.getString("address"));
                return (T) student;
            }
        } catch (SQLException e) {

        }

        return null;
    }
}
