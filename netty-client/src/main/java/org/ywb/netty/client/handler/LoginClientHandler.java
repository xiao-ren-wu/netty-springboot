package org.ywb.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.packet.request.LoginRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/1 3:56 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class LoginClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("张三");
        loginRequestPacket.setPassword("pwf");
        loginRequestPacket.setUserId("1");
        ByteBuf encode = PacketCodeC.encode(loginRequestPacket);
        ctx.channel().writeAndFlush(encode);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = PacketCodeC.decode((ByteBuf) msg);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            log.info("{}:{}", loginResponsePacket.getSuccess(),loginResponsePacket.getReason());
        }
    }
}
