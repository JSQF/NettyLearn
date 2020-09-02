package com.yyb.learn.character7.p2;

import com.yyb.learn.character7.MsgPackDeconder;
import com.yyb.learn.character7.MsgPackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-01
 * @Time 15:08
 */
public class EchoClient {
    public void connect(String host, int port) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //根据消息长度 分割 包
                            ch.pipeline().addLast("frameDeconder",
                                    new LengthFieldBasedFrameDecoder(65535,
                                            0, 2, 0, 2));
                            ch.pipeline().addLast("msg decoder", new MsgPackDeconder());
                            //在 ByteBuf 之前增加 2个字节的 消息长度
                            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            ch.pipeline().addLast("msg encoder", new MsgPackEncoder());
                            ch.pipeline().addLast(new EchoClientHandler(100));
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient().connect("127.0.0.1", 8080);
    }
}
