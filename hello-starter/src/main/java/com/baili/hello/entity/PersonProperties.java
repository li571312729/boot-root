package com.baili.hello.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@ConfigurationProperties(prefix = "spring.person")
@Data
public class PersonProperties {
    // 姓名
    private String name;
    // 年龄
    private int age;
    // 性别
    private String sex = "M";
}
