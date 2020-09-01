package com.yyb.learn.character4.delimiterDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 09:45
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //增加了 String 编码器 所以 这里可以直接 强转 String了
        String msgReq  = (String)msg;
        //增加了 delimiter 编码器 所以这个不用再做 任何处理了
        System.out.println("The time server receive order: " + msgReq + "; The conter is :" + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(msgReq) ?
                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime += System.getProperty("line.separator");
        ByteBuf rep = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(rep);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
