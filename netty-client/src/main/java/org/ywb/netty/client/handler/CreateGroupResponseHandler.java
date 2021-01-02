package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.CreateGroupResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:21 下午 星期六
 * @since 1.0.0
 */
@Slf4j
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        Boolean success = createGroupResponsePacket.getSuccess();
        if (success) {
            log.info("{}", createGroupResponsePacket.getReason());
        } else {
            log.error(createGroupResponsePacket.getReason());
        }
    }
}
