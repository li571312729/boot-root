package com.baili.boot.test;

import com.baili.netty.annation.EnableNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author baili
 * @date 2021-12-07
 */
@EnableNettyServer
@SpringBootApplication
public class BootApplication {

    public static void main( String[] args ) {
        SpringApplication.run(BootApplication.class, args);
    }
}
