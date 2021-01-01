package org.ywb.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.packet.request.LoginRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;
import org.ywb.netty.common.utils.LoginUtil;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:18 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

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
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        log.info("{}:{}", loginResponsePacket.getSuccess(), loginResponsePacket.getReason());
        if(loginResponsePacket.getSuccess()){
            // 在channel中插入登录标记
            log.info("登录成功，插入登录标记");
            LoginUtil.markAsLogin(ctx.channel());
        }
    }
}
