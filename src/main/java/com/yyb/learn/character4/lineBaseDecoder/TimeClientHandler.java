package com.yyb.learn.character4.lineBaseDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 08:40
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
    private final byte[] req;
    private int count;

    public TimeClientHandler(){
        req = ("QUERY TIME ORDER " + System.getProperty("line.separator")).getBytes();
        
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //String 编码器 已经处理过了
        String body = (String)msg;
        System.out.println("Now is: " + body + "; The counter is : " + ++count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<100;i++){
            ByteBuf firstMessage = Unpooled.buffer(req.length);
            firstMessage.writeBytes(req);
            ctx.writeAndFlush(firstMessage);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexcepted exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
