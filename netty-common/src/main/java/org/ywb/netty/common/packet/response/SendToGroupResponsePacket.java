package org.ywb.netty.common.packet.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.ResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/3 1:21 下午 星期日
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class SendToGroupResponsePacket extends ResponsePacket {

    private String msg;

    @Override
    public Command command() {
        return Command.RESPONSE_SEND_TO_GROUP;
    }
}
