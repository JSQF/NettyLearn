package com.yyb.learn.character4.packetSplicing;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 08:30
 */
public class TineServerHandler extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req); //注意这里是 readBytes
        String body = new String(req, "UTF-8")
                .substring(0, req.length - System.getProperty("line.separator").length());

        System.out.println("The time server receive order: " + body + "; The conter is :" + ++counter);
        String currentTime = "QUERY TIME ORDER ".equalsIgnoreCase(body) ?
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
