package org.ywb.netty.common.packet.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:53 下午 星期六
 * @since 1.0.0
 */
@Data
@Component
public class JoinGroupRequestPacket implements Packet {

    private String joinGroupName;

    @Override
    public Command command() {
        return Command.REQUEST_JOIN_GROUP;
    }
}
