package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.QuitGroupResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/2 5:49 下午 星期六
 * @since 1.0.0
 */
@Slf4j
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        Boolean success = quitGroupResponsePacket.getSuccess();
        if (success) {
            log.info(quitGroupResponsePacket.getReason());
        } else {
            log.error(quitGroupResponsePacket.getReason());
        }
    }
}
