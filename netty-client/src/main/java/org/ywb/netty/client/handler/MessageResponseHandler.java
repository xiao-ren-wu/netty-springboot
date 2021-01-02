package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.MessageResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:19 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        Boolean success = messageResponsePacket.getSuccess();
        if (success) {
            log.info(messageResponsePacket.getMessage());
        } else {
            log.error(messageResponsePacket.getReason());
        }
    }
}
