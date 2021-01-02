package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;
import org.ywb.netty.common.packet.request.LoginRequestPacket;

import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/2 4:28 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Console(command = ConsoleCommand.login)
public class LoginConsoleCommandExec implements ConsoleCommandExec {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("请输入登录名:");
        String username = scanner.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username);
        channel.writeAndFlush(loginRequestPacket);
        log.info("用户[{}]登录中...", username);
    }
}
