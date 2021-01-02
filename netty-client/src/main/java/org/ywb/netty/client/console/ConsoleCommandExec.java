package org.ywb.netty.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author yuwenbo1
 * @date 2021/1/2 2:44 下午 星期六
 * @since 1.0.0
 * 控制台指令执行
 */
public interface ConsoleCommandExec {
    /**
     * 执行指令
     *
     * @param scanner sc
     * @param channel chan
     */
    void exec(Scanner scanner, Channel channel);
}
