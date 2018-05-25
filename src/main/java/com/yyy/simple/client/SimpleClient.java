package com.yyy.simple.client;

import com.yyy.base.BaseClient;
import com.yyy.simple.client.handle.SimpleClientHandle;
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

/**
 * @author yinyiyun
 * @date 2018/5/23 11:02
 */
public class SimpleClient extends BaseClient {

    @Override
    protected ChannelInitializer<SocketChannel> getChannel() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ObjectEncoder());
                ch.pipeline().addLast(new ObjectDecoder(
                        1024 * 1024,
                        ClassResolvers.weakCachingConcurrentResolver(
                                this.getClass().getClassLoader()
                        )
                ));
                ch.pipeline().addLast(new SimpleClientHandle());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        SimpleClient client = new SimpleClient();
        client.connect("127.0.0.1", 9999);
    }

}
