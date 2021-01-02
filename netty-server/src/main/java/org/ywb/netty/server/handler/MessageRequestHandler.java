package org.ywb.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.MessageRequestPacket;
import org.ywb.netty.common.packet.response.MessageResponsePacket;
import org.ywb.netty.common.utils.SessionUtil;

import java.util.Objects;

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
        String message = messageRequestPacket.getMessage();
        String[] split = message.split(":");
        Channel channel = SessionUtil.getChannel(split[0]);
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        if (Objects.isNull(channel)) {
            messageResponsePacket.setSuccess(false);
            messageResponsePacket.setReason(split[0] + ":对方未在线");
            ctx.channel().writeAndFlush(messageResponsePacket);
        } else {
            messageResponsePacket.setSuccess(true);
            messageResponsePacket.setMessage(SessionUtil.getSession(ctx.channel()).getUsername() + ":" + split[1]);
            channel.writeAndFlush(messageResponsePacket);
        }
    }
}
