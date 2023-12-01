package com.github.lianyutian.mybatisdemo;

import java.util.List;

public interface UserMapper {
    List<User> queryUserBySchoolName(String schoolName);
}
