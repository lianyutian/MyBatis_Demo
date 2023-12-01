package com.github.lianyutian.mybatisdemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author : [lm]
 * @version : [v1.0]
 * @createTime : [2023/11/30 20:54]
 */
public class MyBatisDemoTest {
    @Test
    public void test() throws IOException {
        // 第一阶段：MyBatis初始化阶段
        // 读取 MyBatis 配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 第二阶段：数据处理阶段
        // 打开一个 SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取 Mapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setSchoolName("清华大学");
            // 调用 Mapper 中的方法
            List<User> users = userMapper.queryUserBySchoolName(user.getSchoolName());

            // 处理查询结果
            for (User user1 : users) {
                System.out.println(user1.getName() + ": " + user1.getSchoolName());
            }

        }
    }

}
