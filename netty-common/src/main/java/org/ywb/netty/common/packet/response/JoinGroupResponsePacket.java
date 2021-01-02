package org.ywb.netty.common.packet.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.ResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/2 5:03 下午 星期六
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class JoinGroupResponsePacket extends ResponsePacket {
    @Override
    public Command command() {
        return Command.RESPONSE_JOIN_GROUP;
    }
}
