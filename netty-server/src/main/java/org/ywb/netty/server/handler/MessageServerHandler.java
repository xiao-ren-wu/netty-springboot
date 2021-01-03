package org.ywb.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.packet.request.LoginRequestPacket;
import org.ywb.netty.common.packet.request.MessageRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;
import org.ywb.netty.common.packet.response.MessageResponsePacket;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/1 4:01 下午 星期五
 * @since 1.0.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class MessageServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            handlerLogin((LoginRequestPacket) packet, ctx);
        } else if (packet instanceof MessageRequestPacket) {
            handlerMessage((MessageRequestPacket) packet, ctx);
        }
    }

    private void handlerMessage(MessageRequestPacket packet, ChannelHandlerContext ctx) {
        log.info("服务端接收消息[{}]", packet);
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setReason("接收成功");
        messageResponsePacket.setSuccess(true);
        ctx.writeAndFlush(PacketCodeC.encode(messageResponsePacket));
    }

    private void handlerLogin(LoginRequestPacket requestPacket, ChannelHandlerContext ctx) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(requestPacket)) {
            log.info("[{}]登录成功", requestPacket.getUsername());
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setReason("登录成功");
        } else {
            log.error("[{}]登录失败", requestPacket.getUsername());
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登录失败");
        }
        ByteBuf encode = PacketCodeC.encode(loginResponsePacket);
        ctx.channel().writeAndFlush(encode);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
