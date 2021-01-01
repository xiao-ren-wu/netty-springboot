package org.ywb.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.packet.request.LoginRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:06 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            log.info("[{}]登录成功", loginRequestPacket.getUsername());
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setReason("登录成功");
        } else {
            log.error("[{}]登录失败", loginRequestPacket.getUsername());
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登录失败");
        }
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
