package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.packet.response.JoinGroupResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/2 5:14 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Component
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        Boolean success = joinGroupResponsePacket.getSuccess();
        if (success) {
            log.info(joinGroupResponsePacket.getReason());
        } else {
            log.error(joinGroupResponsePacket.getReason());
        }
    }
}
