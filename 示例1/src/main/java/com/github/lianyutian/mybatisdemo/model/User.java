package com.github.lianyutian.mybatisdemo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private Integer sex;
    private String schoolName;
}
