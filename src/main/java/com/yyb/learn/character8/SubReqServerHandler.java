package com.yyb.learn.character8;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-02
 * @Time 10:26
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if("Lilinfeng".equalsIgnoreCase(req.getUserName())){
            System.out.println("Server accept client subscribe req : [" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeResProto.SubscribeResp resp(int subReqID){
        SubscribeResProto.SubscribeResp.Builder builder = SubscribeResProto.SubscribeResp.newBuilder();
        builder.setSubReqId(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order Success");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
