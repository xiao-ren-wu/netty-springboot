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

    ;

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
