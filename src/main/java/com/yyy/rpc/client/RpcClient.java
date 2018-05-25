package com.yyy.rpc.client;

import com.yyy.base.RpcRequest;
import com.yyy.po.Person;
import com.yyy.rpc.client.handle.RpcClientHandle;
import com.yyy.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:29
 */
public class RpcClient {

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz, String host, int port) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                (proxy, method, args) -> {
                    RpcRequest request = new RpcRequest();
                    request.setRequestId(UUID.randomUUID().toString());
                    request.setClassName(clazz.getName());
                    request.setMethodName(method.getName());
                    request.setParameters(args);
                    request.setParameterTypes(method.getParameterTypes());

                    RpcClientHandle handle = new RpcClientHandle();
                    EventLoopGroup workerGroup = new NioEventLoopGroup();
                    try {
                        Bootstrap b = new Bootstrap();
                        b.group(workerGroup);
                        b.channel(NioSocketChannel.class);
                        b.option(ChannelOption.SO_KEEPALIVE, true);
                        b.handler(getChannel(handle));

                        // Start the client.
                        ChannelFuture f = b.connect(host, port).sync();

                        f.channel().writeAndFlush(request);
                        // Wait until the connection is closed.
                        f.channel().closeFuture().sync();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        workerGroup.shutdownGracefully();
                    }
                    return handle.getResult();
                }
        );
    }

    private ChannelInitializer<SocketChannel> getChannel(RpcClientHandle handle) {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {

                //添加对象解码器 禁止缓存类加载器
                ch.pipeline().addLast(new ObjectDecoder(
                        1024 * 1024,
                        ClassResolvers.weakCachingConcurrentResolver(
                                this.getClass().getClassLoader())
                ));
                //设置发送消息编码器(传输序列号对象)
                ch.pipeline().addLast(new ObjectEncoder());

                ch.pipeline().addLast(handle);
            }
        };
    }

    public static void main(String[] args) throws Exception {
        RpcClient client = new RpcClient();
        HelloService helloService = client.create(HelloService.class, "127.0.0.1", 9999);
        Person person = helloService.getPerson("1", "YYY");
        System.out.println(person.getName());
        System.out.println(helloService.hello(new Person("2", "KKK")));
    }

}
