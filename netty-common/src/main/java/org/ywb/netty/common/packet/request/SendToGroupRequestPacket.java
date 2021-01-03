package org.ywb.netty.common.packet.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/3 1:12 下午 星期日
 * @since 1.0.0
 */
@Data
@Component
public class SendToGroupRequestPacket implements Packet {

    private String groupName;

    private String msg;

    @Override
    public Command command() {
        return Command.REQUEST_SEND_TO_GROUP;
    }
}
