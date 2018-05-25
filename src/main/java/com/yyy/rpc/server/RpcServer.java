package com.yyy.rpc.server;

import com.yyy.base.BaseServer;
import com.yyy.rpc.server.handle.RpcServerHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:28
 */
public class RpcServer extends BaseServer {

    public RpcServer(int port) {
        super(port);
    }

    @Override
    public ChannelInitializer<SocketChannel> getChannel() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new RpcServerHandle());
            }
        };
    }

    public static void main(String[] args) {
        try {
            new RpcServer(9999).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
