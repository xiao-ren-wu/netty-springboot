package org.ywb.netty.common.packet.response;

import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.ResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:10 下午 星期六
 * @since 1.0.0
 */
@Component
public class CreateGroupResponsePacket extends ResponsePacket {

    @Override
    public Command command() {
        return Command.RESPONSE_CREATE_GROUP;
    }
}
