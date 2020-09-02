package com.yyb.learn.character9;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2019-04-04
 * @Time 11:21
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0; i<10; i++){
            System.out.println("Client send request");
            ctx.writeAndFlush(subReq(i));
        }
    }

    private SubscribeReq subReq(int i){
        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSubReqID(i);
        subscribeReq.setUserName("Lilinfeng");
        subscribeReq.setProductName("Netty Book for Marshalling");
        subscribeReq.setAddress("NanJing YuHuaTai");
        subscribeReq.setPhoneNumber("138xxxxxxx");
        return subscribeReq;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive Server Response : [" + msg.toString() + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getCause());
        ctx.close();
    }
}
