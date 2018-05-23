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
//
//        ByteBuf result = (ByteBuf) msg;
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//        result.release();
//
//        // 接收并打印客户端的信息
//        System.out.println("SimpleOutHandle2 Server send:" + new String(result1));
//
        super.write(ctx, msg, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
