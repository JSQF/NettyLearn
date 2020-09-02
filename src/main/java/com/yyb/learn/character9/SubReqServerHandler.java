package com.yyb.learn.character9;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2019-04-04
 * @Time 10:55
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server received msg");
        SubscribeReq req = (SubscribeReq)msg;
        if("Lilinfeng".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service receive client subscribe req: [" + req.toString() + "]");
            ctx.write(resp(req.getSubReqID()));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private SubscribeResp resp(int subReqId){
        SubscribeResp subscribeResp = new SubscribeResp();
        subscribeResp.setSubReqID(subReqId);
        subscribeResp.setRespCode(0);
        subscribeResp.setDesc("Netty Book order succeed , 3 days later sent to the designed address");
        return subscribeResp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getCause());
        ctx.close();
    }
}
