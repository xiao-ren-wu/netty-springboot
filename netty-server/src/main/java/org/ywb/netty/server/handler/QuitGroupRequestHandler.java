package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.QuitGroupRequestPacket;
import org.ywb.netty.common.packet.response.QuitGroupResponsePacket;
import org.ywb.netty.common.utils.GroupUtil;
import org.ywb.netty.common.utils.MessageUtil;
import org.ywb.netty.common.utils.SessionUtil;

/**
 * @author yuwenbo1
 * @date 2021/1/2 5:39 下午 星期六
 * @since 1.0.0
 */
@Slf4j
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String quitGroup = quitGroupRequestPacket.getQuitGroup();
        boolean success = GroupUtil.quitGroup(quitGroup, channelHandlerContext.channel());
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        String username = SessionUtil.getSession(channelHandlerContext.channel()).getUsername();
        if (success) {
            quitGroupResponsePacket.setSuccess(true);
            log.info("用户[{}],退出[{}]群聊", username, quitGroup);
            quitGroupResponsePacket.setReason("用户[" + username + "]退出群聊[" + quitGroup + "]");
            MessageUtil.broadcast(quitGroupResponsePacket, GroupUtil.channelList(quitGroup));
        } else {
            log.info("用户[{}],退出[{}]群聊失败，群聊已解散或不存在", username, quitGroup);
            quitGroupResponsePacket.setReason(String.format("用户[%s],退出[%s]群聊失败，群聊已解散或不存在", username, quitGroup));
            quitGroupResponsePacket.setSuccess(true);
        }
        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
