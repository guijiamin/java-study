package com.study.networkprogram.netty.helloworld;

import io.netty.channel.*;

import java.net.InetAddress;
import java.util.Date;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2019/8/8.
 *
 * @author guijiamin.
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("it is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
        String response;
        boolean close = false;
        if (request.isEmpty()) {
            response = "please type something.\r\n";
        } else if ("bye".equals(request.toLowerCase())) {
            response = "hava a good day!\r\n";
            close = true;
        } else {
            response = "did you say " + request + "?\r\n";
        }
        ChannelFuture future = ctx.write(response);
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
