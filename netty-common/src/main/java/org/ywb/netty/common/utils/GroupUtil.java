package org.ywb.netty.common.utils;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import org.springframework.boot.web.servlet.server.Session;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author yuwenbo1
 * @date 2021/1/2 3:58 下午 星期六
 * @since 1.0.0
 */
public class GroupUtil {

    private static final Map<String, Set<Channel>> GROUP = new ConcurrentHashMap<>();

    private static final ReentrantLock LOCK = new ReentrantLock();

    /**
     * 创建群聊
     *
     * @param groupName   groupName
     * @param channelList 用户列表
     * @return bool
     */
    public static boolean createGroup(String groupName, Set<Channel> channelList) {
        LOCK.lock();
        try {
            Set<Channel> channels = GROUP.get(groupName);
            if (Objects.nonNull(channels)) {
                return false;
            }
            GROUP.put(groupName, channelList);
        } finally {
            LOCK.unlock();
        }
        return true;
    }

    public static boolean joinGroup(String joinGroupName, Channel channel) {
        Set<Channel> channels = GROUP.get(joinGroupName);
        if (Objects.isNull(channels)) {
            return false;
        }
        channels.add(channel);
        return true;
    }


    public static Set<Channel> channelList(String groupName) {
        return GROUP.get(groupName);
    }

    public static boolean quitGroup(String quitGroup, Channel channel) {
        Set<Channel> channels = GROUP.get(quitGroup);
        if (Objects.isNull(channels)) {
            return false;
        }
        channels.remove(channel);
        if (channels.isEmpty()) {
            // 没有群成员，解散
            GROUP.remove(quitGroup);
        }
        return true;
    }
}
