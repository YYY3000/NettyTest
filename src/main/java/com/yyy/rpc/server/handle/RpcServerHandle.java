package com.yyy.rpc.server.handle;

import com.yyy.base.RpcRequest;
import com.yyy.base.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:30
 */
public class RpcServerHandle extends ChannelInboundHandlerAdapter {

    private Map<String, Class> serviceMap;

    public RpcServerHandle(Map<String, Class> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        RpcResponse response = invoke(request);
        ctx.write(response);
        ctx.flush();
    }

    private RpcResponse invoke(RpcRequest request) throws Exception {
        System.out.println(request.getClassName());
        // 不能通过接口类名实例化实现类
        Object claszz = serviceMap.get(request.getClassName()).newInstance();
        Method method = claszz.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
        Object result = method.invoke(claszz, request.getParameters());
        RpcResponse response = new RpcResponse();
        response.setResult(result);
        response.setRequestId(request.getRequestId());
        return response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
