package org.ywb.netty.common.packet.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/2 5:33 下午 星期六
 * @since 1.0.0
 */
@Data
@Component
public class GroupListRequestPacket implements Packet {

    private String groupName;

    @Override
    public Command command() {
        return Command.REQUEST_LIST_GROUP;
    }
}
