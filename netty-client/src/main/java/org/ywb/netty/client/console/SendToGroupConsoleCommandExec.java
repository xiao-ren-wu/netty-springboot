package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;
import org.ywb.netty.common.packet.request.SendToGroupRequestPacket;

import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/3 1:07 下午 星期日
 * @since 1.0.0
 */
@Slf4j
@Console(command = ConsoleCommand.sendToGroup)
public class SendToGroupConsoleCommandExec implements ConsoleCommandExec {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("请输入要发送的群组，示例:${群组名称}:${消息}");
        String sendToGroupMsg = scanner.nextLine();
        String[] split = sendToGroupMsg.split(":");
        SendToGroupRequestPacket sendToGroupRequestPacket = new SendToGroupRequestPacket();
        sendToGroupRequestPacket.setGroupName(split[0]);
        sendToGroupRequestPacket.setMsg(split[1]);
        channel.writeAndFlush(sendToGroupRequestPacket);
    }
}
