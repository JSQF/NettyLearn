package com.yyb.learn.character9;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-02
 * @Time 21:17
 */
public class MarchshallingCodeCfactory {
    /**
     * 创建 Marshalling 解码器
     * @return
     */
    public static MarshallingDecoder buildMarshallingDecoder(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, marshallingConfiguration);
        MarshallingDecoder marshallingDecoder = new MarshallingDecoder(provider, 1024);
        return  marshallingDecoder;
    }

    /**
     * 创建 Marshalling 编码器
     * @return
     */
    public static MarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, marshallingConfiguration);
        MarshallingEncoder marshallingEncoder = new MarshallingEncoder(provider);
        return  marshallingEncoder;
    }
}
