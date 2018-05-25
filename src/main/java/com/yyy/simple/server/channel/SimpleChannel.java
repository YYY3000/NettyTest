package com.yyy.simple.server.channel;

import com.yyy.simple.server.handle.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yinyiyun
 * @date 2018/5/23 13:45
 */
public class SimpleChannel extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // OutHandle 必须至少在一个InHandle之前 否则不执行
        // OutHandle 按加载顺序倒序执行
        // 最后一个OutHandle不需要调用super方法
        // OutHandle 调用ctx的write时会走下一个 OutHandle 的write方法
        pipeline.addLast(new SimpleOutHandle2());
        pipeline.addLast(new SimpleOutHandle1());

        // InHandle 按加载顺序顺序执行
        // 最后一个InHandle不需要调用super方法
        // InHandle 调用ctx的write时会先走 OutHandle 再继续执行
        pipeline.addLast(new SimpleServerHandle());
        pipeline.addLast(new SimpleInHandle1());
        pipeline.addLast(new SimpleInHandle2());
    }
}
