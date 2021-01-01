package org.ywb.netty.common.enums;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.protocol.Packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author yuwenbo1
 * @date 2021/1/1 4:25 下午 星期五
 * @since 1.0.0
 */
@Slf4j
@Component
public class CommandPacketMap implements ApplicationContextAware {

    private static final HashMap<Command, Packet> PACKET_CONTAINER = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Packet> packetMap = applicationContext.getBeansOfType(Packet.class);
        if (packetMap.isEmpty()) {
            log.warn("no packet bean");
            return;
        }
        packetMap.values().forEach(packet -> PACKET_CONTAINER.put(packet.command(), packet));
        log.info("finish register packet bean");
    }

    public static Class<? extends Packet> from(Command command) {
        return Optional.ofNullable(PACKET_CONTAINER.get(command))
                .map(Packet::getClass)
                .orElseThrow(() -> new IllegalArgumentException("command [" + command + "] not found"));
    }
}
