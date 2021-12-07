package com.baili.netty.client;

import com.baili.netty.annation.EnableNettyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author baili
 * @date 2021-12-07
 */
@EnableNettyClient
@SpringBootApplication
public class BootApplication {

    public static void main( String[] args ) {
        SpringApplication.run(BootApplication.class, args);
    }
}
