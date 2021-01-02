package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.request.GroupListRequestPacket;
import org.ywb.netty.common.packet.response.GroupListResponsePacket;
import org.ywb.netty.common.utils.GroupUtil;

import java.util.List;

/**
 * @author yuwenbo1
 * @date 2021/1/2 10:06 下午 星期六
 * @since 1.0.0
 */
@Slf4j
public class GroupListRequestHandler extends SimpleChannelInboundHandler<GroupListRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupListRequestPacket groupListRequestPacket) throws Exception {
        String groupName = groupListRequestPacket.getGroupName();
        List<String> userList = GroupUtil.groupList(groupName);
        log.info("获取群组[{}]列表{}", groupName, userList);
        GroupListResponsePacket groupListResponsePacket = new GroupListResponsePacket();
        groupListResponsePacket.setUsers(userList);
        ctx.channel().writeAndFlush(groupListResponsePacket);
    }
}
