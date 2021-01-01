package org.ywb.netty.common.packet.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/1 6:16 下午 星期五
 * @since 1.0.0
 */
@Data
@Component
public class MessageRequestHandler implements Packet {

    private String message;

    @Override
    public Command command() {
        return Command.REQUEST_MESSAGE;
    }
}
