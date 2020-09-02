package com.yyb.learn.character8;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-02
 * @Time 11:33
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
    private SubscribeReqProto.SubscribeReq subReq(int i){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i);
        builder.setUserName("Lilinfeng");
        builder.setProductName("Netty Book For ProtoBuf");
        List<String> address = new ArrayList<String>();
        address.add("Nan jing");
        address.add("Bei jing");
        address.add("Dong jing");
        builder.addAllAddress(address);
        return builder.build();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i =0 ;i< 10;i++){
            //这里写的数据 已经是虚列好的
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response: ["+ msg + "]");
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
