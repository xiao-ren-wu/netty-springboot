package org.ywb.netty.common.packet.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.protocol.Packet;

import java.util.List;

/**
 * @author yuwenbo1
 * @date 2021/1/2 2:51 下午 星期六
 * @since 1.0.0
 */
@Data
@Component
public class CreateGroupRequestPacket implements Packet {

    /**
     * 群聊名称
     */
    private String groupName;
    /**
     * 人员列表
     */
    private List<String> users;

    @Override
    public Command command() {
        return Command.REQUEST_CREATE_GROUP;
    }
}
