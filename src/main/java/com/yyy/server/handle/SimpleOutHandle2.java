package com.yyy.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author yinyiyun
 * @date 2018/5/23 13:48
 */
public class SimpleOutHandle2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("===SimpleOutHandle2=== write");

        ByteBuf result = (ByteBuf) msg;
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);

        String message = new String(result1);
        // 接收并打印客户端的信息
        System.out.println("SimpleOutHandle1 Server send:" + message);

        // 释放资源，这行很关键
        result.release();

        message = "SimpleOutHandle1 " + message;
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(4 * message.length());
        encoded.writeBytes(message.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
