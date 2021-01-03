package org.ywb.netty.common.packet.request;

import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

/**
 * @author yuwenbo1
 * @date 2021/1/3 2:41 下午 星期日
 * @since 1.0.0
 */
@Component
public class HeartBeatRequestPacket implements Packet {

    @Override
    public Command command() {
        return Command.REQUEST_HEARTBEAT;
    }
}
