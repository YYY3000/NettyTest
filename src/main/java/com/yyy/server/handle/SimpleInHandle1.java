package com.yyy.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yinyiyun
 * @date 2018/5/23 13:48
 */
public class SimpleInHandle1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("===SimpleInHandle1=== channelRead");

//        ByteBuf result = (ByteBuf) msg;
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//
//        // 接收并打印客户端的信息
//        System.out.println("SimpleInHandle1 Client said:" + new String(result1));
//
//        // 释放资源，这行很关键
//        result.release();
//
//
//        // 向客户端发送消息
//        String response = "SimpleInHandle1 hello client! \n";
//
//        // 在当前场景下，发送的数据必须转换成ByteBuf数组
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);

        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===SimpleInHandle1=== channelReadComplete");
        ctx.flush();
        super.channelReadComplete(ctx);
    }
}