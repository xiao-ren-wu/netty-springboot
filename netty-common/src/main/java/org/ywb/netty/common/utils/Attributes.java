package org.ywb.netty.common.utils;

import io.netty.util.AttributeKey;

/**
 * @author yuwenbo1
 * @date 2021/1/2 1:43 下午 星期六
 * @since 1.0.0
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
