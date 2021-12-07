package com.baili.boot.test.controller;

import com.baili.hello.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private PersonService personService;

    @GetMapping("")
    public String hello(){
        log.info("hello world interface invoke.");
        return personService.sayHello();
    }

}
