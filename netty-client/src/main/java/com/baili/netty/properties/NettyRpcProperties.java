package com.baili.netty.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyRpcProperties {
    private int serverPort = 9000;
}
