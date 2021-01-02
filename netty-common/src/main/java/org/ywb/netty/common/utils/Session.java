package org.ywb.netty.common.utils;

import lombok.Builder;
import lombok.Data;

/**
 * @author yuwenbo1
 * @date 2021/1/2 1:42 下午 星期六
 * @since 1.0.0
 * 用户信息session
 */
@Data
@Builder
public class Session {

    private String userId;

    private String username;
}
