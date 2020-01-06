package com.study.networkprogram.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2019/8/8.
 *
 * @author guijiamin.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1", 8888).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            ChannelFuture lastWriteFuture = null;
            for (;;) {
                String s = reader.readLine();
                if (s == null) break;
                lastWriteFuture = channel.writeAndFlush(s + "\r\n");

                if ("bye".equals(s.toLowerCase())) {
                    channel.closeFuture().sync();
                    break;
                }

                if (lastWriteFuture != null) {
                    lastWriteFuture.sync();
                }
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
