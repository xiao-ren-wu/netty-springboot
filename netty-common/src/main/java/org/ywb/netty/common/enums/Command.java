package org.ywb.netty.common.enums;

import lombok.Getter;
import org.ywb.netty.common.protocol.Packet;

import java.util.stream.Stream;

/**
 * @author yuwenbo1
 * @date 2021/1/1 1:23 下午 星期五
 * @since 1.0.0
 */
@Getter
public enum Command {
    /**
     * 登录
     */
    REQUEST_LOGIN(1),
    RESPONSE_LOGIN(2),
    /**
     * 客户端收发消息
     */
    REQUEST_MESSAGE(3),
    RESPONSE_MESSAGE(4),
    /**
     * 创建群聊
     */
    REQUEST_CREATE_GROUP(5),
    RESPONSE_CREATE_GROUP(6),

    /**
     * 创建群聊
     */
    REQUEST_JOIN_GROUP(7),
    RESPONSE_JOIN_GROUP(8),

    /**
     * 退出群聊
     */
    REQUEST_QUIT_GROUP(9),
    RESPONSE_QUIT_GROUP(10),

    /**
     * 群聊列表
     */
    REQUEST_LIST_GROUP(11),
    RESPONSE_LIST_GROUP(12),
    /**
     * 发送消息到群组
     */
    REQUEST_SEND_TO_GROUP(13),
    RESPONSE_SEND_TO_GROUP(14);
    Byte code;

    Command(int code) {
        this.code = Byte.parseByte(String.valueOf(code));
    }

    public static Command of(byte code) {
        return Stream.of(Command.values())
                .filter(command -> command.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("command code [" + code + "] not found"));
    }

    public static Class<? extends Packet> from(byte code) {
        Command command = of(code);
        return CommandPacketMap.from(command);
    }
}
