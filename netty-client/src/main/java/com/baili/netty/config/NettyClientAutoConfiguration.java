package com.baili.netty.config;

import com.baili.netty.client.NettyClient;
import com.baili.netty.properties.NettyRpcProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Configuration
@EnableConfigurationProperties(NettyRpcProperties.class)
public class NettyClientAutoConfiguration {

    private final NettyRpcProperties nettyRpcProperties;

    @Bean
    public NettyClient nettyClient() {
        return new NettyClient();
    }

    @Autowired
    public NettyClientAutoConfiguration(NettyRpcProperties nettyRpcProperties) {
        this.nettyRpcProperties = nettyRpcProperties;
    }

    @Bean
    public NettyClientBeanPostProcessor nettyClientBeanPostProcessor(NettyClient nettyClient) {
        return new NettyClientBeanPostProcessor(nettyClient, nettyRpcProperties);
    }
}