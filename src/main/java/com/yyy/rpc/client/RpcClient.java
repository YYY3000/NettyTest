package com.yyy.rpc.client;

import com.yyy.base.BaseClient;
import com.yyy.rpc.client.handle.RpcClientHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:29
 */
public class RpcClient extends BaseClient {

    @Override
    protected ChannelInitializer<SocketChannel> getChannel() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new RpcClientHandle());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        RpcClient client = new RpcClient();
        client.connect("127.0.0.1", 9999);
    }

}
