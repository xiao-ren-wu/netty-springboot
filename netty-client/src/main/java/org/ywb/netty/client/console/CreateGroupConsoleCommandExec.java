package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;
import org.ywb.netty.common.packet.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/2 2:47 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Console(command = ConsoleCommand.createGroup)
public class CreateGroupConsoleCommandExec implements ConsoleCommandExec {

    private static final String USER_ID_SPLITTER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("[创建群聊]请输入群聊的名称，用户列表，${groupName}:${user1,user2,user3}");
        String line = scanner.nextLine();
        String[] split = line.split(":");
        String[] usernames = split[1].split(USER_ID_SPLITTER);
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setGroupName(split[0]);
        createGroupRequestPacket.setUsers(Arrays.asList(usernames));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
