package org.ywb.netty.common.protocol;

import lombok.Data;

/**
 * @author yuwenbo1
 * @date 2021/1/1 4:09 下午 星期五
 * @since 1.0.0
 */
@Data
public abstract class ResponsePacket implements Packet {
    /**
     * 状态
     */
    private Boolean success;
    /**
     * 状态描述
     */
    private String reason;
}
