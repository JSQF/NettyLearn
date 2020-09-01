package com.yyb.learn.character7.p2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 16:42
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        UserInfo body = (UserInfo)msg;
//        System.out.println("This is " + ++counter + "times receive client: [" + body + "]");
//        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
