package com.yyy.simple.server;

import com.yyy.base.BaseServer;
import com.yyy.simple.server.channel.SimpleChannel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author yinyiyun
 * @date 2018/5/23 10:57
 */
public class SimpleServer extends BaseServer {

    public SimpleServer(int port) {
        super(port);
    }

    @Override
    protected ChannelInitializer<SocketChannel> getChannel() {
        return new SimpleChannel();
    }

    public static void main(String[] args) {
        try {
            new SimpleServer(9999).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
