DROP TABLE IF EXISTS `user`;
private Integer id;
    private String name;
    private String email;
    private Integer age;
    private Integer sex;
    private String schoolName;
CREATE TABLE `user`  (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
                               `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
                               `age` int NOT NULL COMMENT '年龄',
                               `sex` int NOT NULL COMMENT '性别',
                               `schoolName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校名称',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO mybatis.user (id, name, email, age, sex, schoolName) VALUES (1, '张三', '123@qq.com', 18, 1, '清华大学')
