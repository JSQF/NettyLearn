#NettyLearn
## character3
简答的 timeserver 服务端和客户端  
没有考虑到 粘包、拆包，所以 客户端只能发送一次数据。  

# character4
## packetSplicing
自己在每个消息结尾人为加入 换行符，在 server 端根据 换行符拆解出来；  
客户端 发送多次，但是会发送 粘包，导致发送次数和 接受到的次数不一致；
## lineBaseDecoder
加入了 LineBasedFrameDecoder 和 StringDecoder 编码器，解决  
粘包、拆包问题；还有 String 编码的问题。  
# character5
## delimiterDecoder
固定分隔符分割报文
## fixedlengthDecoder
固定长度的解密器
# character7
## p1
MessagePack 编解码器开发
## p2
MessagePack 编解码器 + 粘包、拆包
# character8
使用 ProtoBuf 的编解码 器（序列化和反序列化）,需要加入 分隔符（粘包、拆包）


#增加 protobuf maven 依赖