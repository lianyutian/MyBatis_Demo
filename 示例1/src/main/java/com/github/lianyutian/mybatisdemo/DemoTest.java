package com.github.lianyutian.mybatisdemo;

import com.github.lianyutian.mybatisdemo.model.User;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemoTest {
    @Test
    public void testQuery() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/mybatis?serverTimezone=UTC";
        String userName = "root";
        String password = "123456";

        User userParam = new User();
        userParam.setId(1);

        // 第一步：加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 第二步：获得数据库的连接
        Connection conn = DriverManager.getConnection(url, userName, password);

        // 第三步：创建语句并执行
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM `user` WHERE id = \'"
                + userParam.getId() + "\';");

        // 第四步：处理数据库操作结果
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setAge(resultSet.getInt("age"));
            user.setSex(resultSet.getInt("sex"));
            user.setSchoolName(resultSet.getString("schoolName"));
            userList.add(user);
        }

        // 第五步：关闭连接
        stmt.close();

        for (User user : userList) {
            System.out.println("name : " + user.getName() + " ;  email : " + user.getEmail());
        }
    }

    @Test
    public void testInsert() {
        String url = "jdbc:mysql://127.0.0.1:3306/mybatis?serverTimezone=UTC";
        String userName = "root";
        String password = "123456";

        // SQL 插入语句
        String insertSql = "INSERT INTO mybatis.user (name, email, age, sex, schoolName) VALUES (?, ?, ?, ?, ?)";

        // 设置是否自动提交事务为 false
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                // 设置参数
                preparedStatement.setString(1, "李四");
                preparedStatement.setString(2, "321@qq.com");
                preparedStatement.setInt(3, 18);
                preparedStatement.setInt(4, 1);
                preparedStatement.setString(5, "北京大学");

                // 执行插入操作
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Insert failed.");
                }

                // 提交事务
                connection.commit();
            } catch (SQLException e) {
                // 回滚事务
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
