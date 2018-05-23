package com.yyy.channel;

import com.yyy.server.handle.*;
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

        // outhandle 必须至少在一个inhandle之前 否则不执行
        pipeline.addLast(new SimpleOutHandle1());
        pipeline.addLast(new SimpleOutHandle2());

        pipeline.addLast(new SimpleServerHandle());
        pipeline.addLast(new SimpleInHandle1());
        pipeline.addLast(new SimpleInHandle2());
    }
}
