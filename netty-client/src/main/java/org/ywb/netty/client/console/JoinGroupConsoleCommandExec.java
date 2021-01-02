package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;
import org.ywb.netty.common.packet.request.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:49 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Console(command = ConsoleCommand.joinGroup)
public class JoinGroupConsoleCommandExec implements ConsoleCommandExec {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("请输入要加入群聊的名称");
        String groupName = scanner.nextLine();
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setJoinGroupName(groupName);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
