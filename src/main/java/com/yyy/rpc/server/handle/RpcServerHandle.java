package com.yyy.rpc.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:30
 */
public class RpcServerHandle extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//        System.out.println("====== channelRead");
//
//        Person p = (Person) msg;
//
//        // 接收并打印客户端的信息
//        System.out.println("SimpleInHandle2 Client said:" + p);
//
//        // 向客户端发送消息
//        Person person = new Person("2", "yyy");
//
//        // 调用write方法则立即走第一个OutHandle的write方法或返回给客户端
//        ctx.write(person);
//        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
