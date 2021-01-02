package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;
import org.ywb.netty.common.packet.request.GroupListRequestPacket;

import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/2 10:03 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Console(command = ConsoleCommand.groupList)
public class GroupListConsoleCommandExec implements ConsoleCommandExec {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("请输入群组名称");
        String groupName = scanner.nextLine();
        GroupListRequestPacket groupListRequestPacket = new GroupListRequestPacket();
        groupListRequestPacket.setGroupName(groupName);
        channel.writeAndFlush(groupListRequestPacket);
    }
}
