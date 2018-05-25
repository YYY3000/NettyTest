package com.yyy.rpc.client.handle;

import com.yyy.base.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:29
 */
public class RpcClientHandle extends ChannelInboundHandlerAdapter {

    private RpcResponse response;

    public RpcClientHandle() {
    }

    public Object getResult() {
        return response.getResult();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取server返回的信息
        response = (RpcResponse) msg;
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
