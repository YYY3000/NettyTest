package com.yyy.simple.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author yinyiyun
 * @date 2018/5/23 13:48
 */
public class SimpleOutHandle1 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("===SimpleOutHandle1=== write");

        // 接收并打印客户端的信息
        System.out.println("SimpleOutHandle1 Server send:" + msg);

        msg = "SimpleOutHandle1 " + msg;

        // 调用write方法则立即走下一个OutHandle的write方法或返回给客户端
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
