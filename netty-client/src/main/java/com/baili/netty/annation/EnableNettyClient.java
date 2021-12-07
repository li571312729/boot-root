package com.baili.netty.annation;

import com.baili.netty.config.NettyClientAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration({NettyClientAutoConfiguration.class})
public @interface EnableNettyClient {
}
