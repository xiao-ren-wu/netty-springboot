package org.ywb.netty.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.ywb.netty.common.codec.PacketCodeC;

import java.util.List;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:03 下午 星期五
 * @since 1.0.0
 */
public class PacketDecodeHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodeC.decode(in));
    }
}
