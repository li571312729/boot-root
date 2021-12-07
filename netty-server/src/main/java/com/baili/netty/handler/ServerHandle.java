package com.baili.netty.handler;

import cn.hutool.json.JSONUtil;
import com.baili.netty.annation.RpcServer;
import com.baili.netty.entity.RpcMessage;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerHandle extends SimpleChannelInboundHandler<RpcMessage> implements ApplicationContextAware {

    private Map<String, Object> serviceMap;

    /**
     * 在类被Spring容器加载时会自动执行setApplicationAware
     *
     * @param applicationContext Spring上下文
     * @throws BeansException 异常信息
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //从Spring容器中获取到所有拥有@RpcServer注解的Beans集合，Map<Name（对象类型，对象全路径名）,实例对象>
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcServer.class);
        log.info("被@RpcServer注解加载的Bean: {}", beansWithAnnotation);
        if (beansWithAnnotation.size() > 0) {
            Map<String, Object> map = new ConcurrentHashMap<>(16);
            for (Object o : beansWithAnnotation.values()) {
                //获取该实例对象实现的接口Class
                Class<?> anInterface = o.getClass().getInterfaces()[0];
                //获取该接口类名，作为Key，实例对象作为Value
                map.put(anInterface.getName(), o);
            }
            //使用变量接住map
            serviceMap = map;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接了: {}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("异常信息");
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage) throws Exception {
        log.info("客户端发送的消息：{}", rpcMessage);
        //从Map中获取实例对象
        Object service = serviceMap.get(rpcMessage.getName());
        //获取调用方法
        Method method = service.getClass().getMethod(rpcMessage.getMethodName(), rpcMessage.getParTypes());
        method.setAccessible(true);
        //反射调用实例对象方法，获取返回值
        Object result = method.invoke(service, rpcMessage.getPars());
        rpcMessage.setResult(JSONUtil.toJsonStr(result));
        log.info("回给客户端的消息：{}", rpcMessage);
        //Netty服务端将数据写会Channel并发送给客户端，同时添加一个监听器，当所有数据包发送完成后，关闭通道
        channelHandlerContext.writeAndFlush(rpcMessage).addListener(ChannelFutureListener.CLOSE);
    }
}
