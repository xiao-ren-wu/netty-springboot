package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.SendToGroupResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/3 1:27 下午 星期日
 * @since 1.0.0
 */
@Slf4j
public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SendToGroupResponsePacket sendToGroupResponsePacket) throws Exception {
        Boolean success = sendToGroupResponsePacket.getSuccess();
        if (success) {
            log.info("接收群消息[{}]", sendToGroupResponsePacket.getMsg());
        } else {
            log.error("群发消息失败[{}]", sendToGroupResponsePacket.getReason());
        }
    }
}
