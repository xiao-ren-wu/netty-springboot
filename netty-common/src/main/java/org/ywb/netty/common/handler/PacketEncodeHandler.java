package org.ywb.netty.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:11 下午 星期五
 * @since 1.0.0
 */
public class PacketEncodeHandler extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.encode(msg,out);
    }
}
