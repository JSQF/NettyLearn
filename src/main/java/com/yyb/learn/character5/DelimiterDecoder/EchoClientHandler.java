package com.yyb.learn.character5.DelimiterDecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 11:36
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int counter;
    private final String echo_req = "Hi, Lilinfeng. Welcome to Netty.$_";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++counter + "times receive : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i <100; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(echo_req.getBytes()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
