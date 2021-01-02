package org.ywb.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.CreateGroupRequestPacket;
import org.ywb.netty.common.packet.response.CreateGroupResponsePacket;
import org.ywb.netty.common.protocol.ResponsePacket;
import org.ywb.netty.common.utils.GroupUtil;
import org.ywb.netty.common.utils.MessageUtil;
import org.ywb.netty.common.utils.SessionUtil;

import java.util.List;
import java.util.Set;

/**
 * @author yuwenbo1
 * @date 2021/1/2 3:53 下午 星期六
 * @since 1.0.0
 */
@Slf4j
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> users = createGroupRequestPacket.getUsers();
        String groupName = createGroupRequestPacket.getGroupName();
        // 获取用户通道
        Set<Channel> channels = SessionUtil.getChannels(users);
        channels.add(ctx.channel());

        boolean success = GroupUtil.createGroup(groupName, channels);
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        if (success) {
            log.info("创建群聊成功,名称[{}],人员{}", groupName, users);
            createGroupResponsePacket.setSuccess(true);
            createGroupResponsePacket.setReason(String.format("成功加入[%s]群聊", groupName));
            MessageUtil.broadcast(createGroupResponsePacket, channels);
        } else {
            log.error("创建群聊失败，群聊名称已存在");
            createGroupResponsePacket.setSuccess(false);
            createGroupResponsePacket.setReason(String.format("创建群聊[%s]失败，名称已群在", groupName));
            ctx.writeAndFlush(createGroupResponsePacket);
        }
    }
}
