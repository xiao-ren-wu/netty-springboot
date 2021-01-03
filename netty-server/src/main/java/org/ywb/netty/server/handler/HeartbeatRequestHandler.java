package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.packet.request.HeartBeatRequestPacket;
import org.ywb.netty.common.packet.response.HeartBeatResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/3 2:45 下午 星期日
 * @since 1.0.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class HeartbeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        log.info("客户端心跳");
        HeartBeatResponsePacket heartBeatResponsePacket = new HeartBeatResponsePacket();
        heartBeatResponsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(heartBeatRequestPacket);
    }
}
