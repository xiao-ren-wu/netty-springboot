package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.HeartBeatResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/3 2:47 下午 星期日
 * @since 1.0.0
 */
@Slf4j
public class HeartbeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatResponsePacket heartBeatResponsePacket) throws Exception {
        log.debug("服务端心跳");
    }
}
