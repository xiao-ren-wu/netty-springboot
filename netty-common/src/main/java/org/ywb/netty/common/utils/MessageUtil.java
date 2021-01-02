package org.ywb.netty.common.utils;

import io.netty.channel.Channel;
import org.ywb.netty.common.protocol.ResponsePacket;

import java.util.Collection;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:17 下午 星期六
 * @since 1.0.0
 */
public class MessageUtil {

    public static void broadcast(ResponsePacket responsePacket, Collection<Channel> channels){
        channels.forEach(channel -> channel.writeAndFlush(responsePacket));
    }
}
