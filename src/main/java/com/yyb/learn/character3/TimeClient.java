package com.yyb.learn.character3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-08-31
 * @Time 16:15
 */
public class TimeClient {
    public void connect(int port, String host) throws Exception{
        //配置客户端线程组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {//这里是 匿名 childChannelHandler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler()); //这里是自己的 业务处理的 handler
                        }
                    });
            //发起异步连接操作
            ChannelFuture f = b.connect(host, port);
            //等待客户端连接关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        int port = 8080;
        String host = "127.0.0.1";
        new TimeClient().connect(port, host);
    }

}
