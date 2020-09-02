package com.yyb.learn.character8;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-02
 * @Time 11:06
 */
public class SubReqClinet {
    public void connect(String host, int port) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //处理返回信息的 channel，所以这里是 SubscribeResProto
                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                            //解码 服务端 发送的 SubscribeResp 信息
                            ch.pipeline().addLast(new ProtobufDecoder(SubscribeResProto.SubscribeResp.getDefaultInstance()));
                            //处理半包
                            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                            //Proto 编码器
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new SubReqClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new SubReqClinet().connect("127.0.0.1", 8080);
    }
}
