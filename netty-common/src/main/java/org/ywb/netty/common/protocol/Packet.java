package org.ywb.netty.common.protocol;

import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.enums.SerializerAlgorithm;

/**
 * @author yuwenbo1
 * @date 2021/1/1 1:14 下午 星期五
 * @since 1.0.0
 * +------+-------+---------—-+----+---------+-----+
 * | 魔数 | 版本号 | 序列化算法 | 指令| 数据长度 | 数据 |
 * +-----+-------+-----------+----+---------+-----+
 * 4字节   1字节  1字节       1字节  4字节     N字节
 */
public interface Packet {

    /**
     * 指令
     *
     * @return {@link Command}
     */
    Command command();

    /**
     * 序列化使用算法
     *
     * @return {@link SerializerAlgorithm}
     */
    default SerializerAlgorithm serializerAlgorithm(){
        return SerializerAlgorithm.JSON;
    }
}
