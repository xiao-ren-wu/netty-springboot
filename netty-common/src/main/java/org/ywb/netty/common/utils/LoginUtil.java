package org.ywb.netty.common.utils;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author yuwenbo1
 * @date 2021/1/1 7:01 下午 星期五
 * @since 1.0.0
 */
public class LoginUtil {

    private static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    public static void markAsLogin(Channel channel) {
        channel.attr(LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.attr(LOGIN).get() != null;
    }
}
