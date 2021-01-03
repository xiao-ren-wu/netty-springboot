package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.packet.request.LoginRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;
import org.ywb.netty.common.utils.LoginUtil;
import org.ywb.netty.common.utils.Session;
import org.ywb.netty.common.utils.SessionUtil;

import java.util.UUID;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:06 下午 星期五
 * @since 1.0.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            // bind session
            Session session = Session.builder()
                    .userId(UUID.randomUUID().toString())
                    .username(loginRequestPacket.getUsername())
                    .build();
            SessionUtil.bindSession(session, channelHandlerContext.channel());

            log.info("[{}]登录成功", loginRequestPacket.getUsername());
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setReason("登录成功");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else {
            log.error("[{}]登录失败", loginRequestPacket.getUsername());
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登录失败");
        }
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 用户断开链接清除session
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return Strings.isNotBlank(loginRequestPacket.getUsername());
    }

}
