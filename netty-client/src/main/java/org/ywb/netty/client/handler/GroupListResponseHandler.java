package org.ywb.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.common.packet.response.GroupListResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/2 10:16 下午 星期六
 * @since 1.0.0
 */
@Slf4j
public class GroupListResponseHandler extends SimpleChannelInboundHandler<GroupListResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupListResponsePacket groupListResponsePacket) throws Exception {
        log.info("群组成员{}", groupListResponsePacket.getUsers());
    }
}
