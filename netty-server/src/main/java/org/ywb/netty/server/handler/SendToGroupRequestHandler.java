package org.ywb.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.SendToGroupRequestPacket;
import org.ywb.netty.common.packet.response.SendToGroupResponsePacket;
import org.ywb.netty.common.utils.GroupUtil;
import org.ywb.netty.common.utils.MessageUtil;

import java.util.Set;

/**
 * @author yuwenbo1
 * @date 2021/1/3 1:18 下午 星期日
 * @since 1.0.0
 */
@Slf4j
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket sendToGroupRequestPacket) throws Exception {
        String groupName = sendToGroupRequestPacket.getGroupName();
        String msg = sendToGroupRequestPacket.getMsg();
        Set<Channel> channels = GroupUtil.channelList(groupName);
        SendToGroupResponsePacket sendToGroupResponsePacket = new SendToGroupResponsePacket();
        if (channels.isEmpty()) {
            log.error("群组[{}]不存在", groupName);
            sendToGroupResponsePacket.setSuccess(false);
            sendToGroupResponsePacket.setReason("群组[" + groupName + "]不存在");
            ctx.channel().writeAndFlush(sendToGroupResponsePacket);
        } else {
            sendToGroupResponsePacket.setSuccess(true);
            log.info("发送群消息，群组[{}],消息[{}]", groupName, msg);
            sendToGroupResponsePacket.setMsg(msg);
            MessageUtil.broadcast(sendToGroupResponsePacket, channels);
        }
    }
}
