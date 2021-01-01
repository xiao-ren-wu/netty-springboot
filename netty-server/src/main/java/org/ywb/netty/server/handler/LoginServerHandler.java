package org.ywb.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.packet.request.LoginRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;
import org.ywb.netty.common.protocol.Packet;
import org.ywb.netty.common.utils.LoginUtil;

/**
 * @author yuwenbo1
 * @date 2021/1/1 4:01 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class LoginServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket requestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            if (valid(requestPacket)) {
                log.info("[{}]登录成功", requestPacket.getUsername());
                // 在channel中插入登录标记
                LoginUtil.markAsLogin(ctx.channel());
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
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
