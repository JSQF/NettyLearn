package com.yyb.learn.character7.p1;

import com.yyb.learn.character7.UserInfo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 15:34
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int sendNumber;
    public EchoClientHandler(int sendNumber){
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(UserInfo userInfo : getUserinfo()){
            ctx.write(userInfo);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client Receive the msgpack message :" + msg);
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

    private UserInfo[] getUserinfo(){
        UserInfo[] userInfos = new UserInfo[this.sendNumber];
        for(int i=0; i< this.sendNumber ;i ++){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("ABCDEFG --->" + i);
            userInfo.setAge(i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
