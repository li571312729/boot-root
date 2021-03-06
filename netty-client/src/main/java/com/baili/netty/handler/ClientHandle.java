package com.baili.netty.handler;

import com.baili.netty.entity.RpcMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentMap;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Slf4j
@ChannelHandler.Sharable
public class ClientHandle extends SimpleChannelInboundHandler<RpcMessage> {
    /**
     * 定义消息Map，将连接通道Channel作为key，消息返回值作为value
     */
    private final ConcurrentMap<Channel, RpcMessage> rpcMessageConcurrentMap;

    public ClientHandle(ConcurrentMap<Channel, RpcMessage> rpcMessageConcurrentMap) {
        this.rpcMessageConcurrentMap = rpcMessageConcurrentMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage) throws Exception {
        log.info("客户端收到服务端消息：{}", rpcMessage);
        rpcMessageConcurrentMap.put(channelHandlerContext.channel(), rpcMessage);
    }
}
