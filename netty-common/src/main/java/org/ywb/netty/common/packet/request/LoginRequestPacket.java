package org.ywb.netty.common.packet.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/1 2:28 下午 星期五
 * @since 1.0.0
 */
@Data
@Component
public class LoginRequestPacket implements Packet {

    private String username;

    private String password;

    private String userId;

    @Override
    public Command command() {
        return Command.REQUEST_LOGIN;
    }

}
