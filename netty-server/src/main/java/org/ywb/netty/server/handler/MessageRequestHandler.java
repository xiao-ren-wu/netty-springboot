package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.MessageRequestPacket;
import org.ywb.netty.common.packet.response.MessageResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:16 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        log.info("服务端接收消息[{}]", messageRequestPacket);
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setReason("接收成功");
        messageResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
