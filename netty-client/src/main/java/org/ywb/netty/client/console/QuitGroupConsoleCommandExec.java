package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;
import org.ywb.netty.common.packet.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/2 5:37 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Console(command = ConsoleCommand.quitGroup)
public class QuitGroupConsoleCommandExec implements ConsoleCommandExec {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("输入退出群聊的名称");
        String quitGroup = scanner.nextLine();
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setQuitGroup(quitGroup);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
