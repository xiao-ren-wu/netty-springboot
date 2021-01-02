package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.LoginResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/1 8:18 下午 星期五
 * @since 1.0.0
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        log.info("{}:{}", loginResponsePacket.getSuccess(), loginResponsePacket.getReason());
        if(loginResponsePacket.getSuccess()){
            log.info("登录成功...");
        }
    }
}
