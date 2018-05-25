package com.yyy.rpc.server;

import com.yyy.base.BaseServer;
import com.yyy.rpc.server.handle.RpcServerHandle;
import com.yyy.service.HelloService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:28
 */
public class RpcServer extends BaseServer {

    public RpcServer(int port) {
        super(port);
    }

    private static final Map<String, Class> serviceMap = new HashMap<>();

    public void addService(String interfaceName, Class clazz) {
        serviceMap.put(interfaceName, clazz);
    }

    @Override
    public ChannelInitializer<SocketChannel> getChannel() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                //添加对象解码器 禁止缓存类加载器
                ch.pipeline().addLast(new ObjectDecoder(
                        1024 * 1024,
                        ClassResolvers.weakCachingConcurrentResolver(
                                this.getClass().getClassLoader())
                ));
                //设置发送消息编码器(传输序列号对象)
                ch.pipeline().addLast(new ObjectEncoder());

                ch.pipeline().addLast(new RpcServerHandle(serviceMap));
            }
        };
    }

    public static void main(String[] args) {
        try {
            System.out.println(HelloService.class.getName());
            RpcServer server = new RpcServer(9999);
            server.addService(HelloService.class.getName(), HelloServiceImpl.class);
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
