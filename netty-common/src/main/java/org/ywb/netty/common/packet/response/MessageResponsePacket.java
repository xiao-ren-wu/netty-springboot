package org.ywb.netty.common.packet.response;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.ResponsePacket;

/**
 * @author yuwenbo1
 * @date 2021/1/1 7:31 下午 星期五
 * @since 1.0.0
 */
@Data
@Component
public class MessageResponsePacket extends ResponsePacket {

    @Override
    public Command command() {
        return Command.RESPONSE_MESSAGE;
    }
}
