package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.packet.request.JoinGroupRequestPacket;
import org.ywb.netty.common.packet.response.JoinGroupResponsePacket;
import org.ywb.netty.common.utils.GroupUtil;
import org.ywb.netty.common.utils.MessageUtil;
import org.ywb.netty.common.utils.SessionUtil;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:57 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String joinGroupName = joinGroupRequestPacket.getJoinGroupName();
        boolean success = GroupUtil.joinGroup(joinGroupName, ctx.channel());
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        String username = SessionUtil.getSession(ctx.channel()).getUsername();
        if (success) {
            joinGroupResponsePacket.setSuccess(true);
            log.info("用户[{}]加入群聊[{}]", username, joinGroupName);
            joinGroupResponsePacket.setReason(String.format("用户[%s]加入群聊[%s]", username, joinGroupName));
            MessageUtil.broadcast(joinGroupResponsePacket, GroupUtil.channelList(joinGroupName));
        } else {
            joinGroupResponsePacket.setSuccess(false);
            log.info("用户[{}]加入群聊[{}]失败，群聊不存在", username, joinGroupName);
            joinGroupResponsePacket.setReason(String.format("用户[%s]加入群聊[%s]失败，群聊不存在", username, joinGroupName));
            ctx.channel().writeAndFlush(joinGroupResponsePacket);
        }
    }
}
