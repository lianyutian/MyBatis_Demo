package com.github.lianyutian.mybatisdemo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryUserBySchoolName(String schoolName);
}
