package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.HeartBeatRequestPacket;
import org.ywb.netty.common.packet.response.LoginResponsePacket;

import java.util.concurrent.TimeUnit;

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
            scheduleSendHeartBeat(ctx);
        }
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        log.info("发送心跳");
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, 5, TimeUnit.SECONDS);
    }
}
