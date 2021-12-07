package com.baili.hello.service;

import com.baili.hello.entity.PersonProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Slf4j
public class PersonService {

    private PersonProperties properties;

    public PersonService() {
    }

    public PersonService(PersonProperties properties) {
        this.properties = properties;
    }

    public String sayHello(){
        log.info("sayHello method invoke!");

        return  "大家好，我叫: " + properties.getName() + ", 今年" + properties.getAge() + "岁"
                + ", 性别: " + properties.getSex();
    }
}
