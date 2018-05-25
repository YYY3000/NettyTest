package com.yyy.simple.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yinyiyun
 * @date 2018/5/23 13:48
 */
public class SimpleInHandle2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("===SimpleInHandle2=== channelRead");

        // 接收并打印客户端的信息
        System.out.println("SimpleInHandle2 Client said:" + msg);

        // 向客户端发送消息
        String response = "SimpleInHandle2 hello client! \n";

        // 调用write方法则立即走第一个OutHandle的write方法或返回给客户端
        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===SimpleInHandle2=== channelReadComplete");
        ctx.flush();
    }

}
