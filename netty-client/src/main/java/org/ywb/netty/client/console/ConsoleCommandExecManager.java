package org.ywb.netty.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.ywb.netty.client.console.annos.Console;
import org.ywb.netty.client.enums.ConsoleCommand;

import java.util.*;


/**
 * @author yuwenbo1
 * @date 2021/1/2 2:45 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Component
public class ConsoleCommandExecManager implements ApplicationContextAware {

    private final Map<ConsoleCommand, ConsoleCommandExec> commandExecContainer = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ConsoleCommandExec> commandExecMap = applicationContext.getBeansOfType(ConsoleCommandExec.class);
        Collection<ConsoleCommandExec> values = commandExecMap.values();
        if (values.isEmpty()) {
            log.warn("no console command exec");
            return;
        }
        values.forEach(a -> {
            Console console = a.getClass().getAnnotation(Console.class);
            if (Objects.isNull(console)) {
                throw new RuntimeException("@Console not found no class [" + a.getClass() + "]");
            }
            ConsoleCommand command = console.command();
            commandExecContainer.put(command, a);
        });
        log.info("finish register console command exec");
    }

    public void execCommand(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();
        ConsoleCommandExec commandExec = null;
        try {
            commandExec = commandExecContainer.get(ConsoleCommand.valueOf(command));
        } catch (IllegalArgumentException e) {
            log.error("指令[" + command + "]未找到");
            return;
        }
        commandExec.exec(scanner, channel);
    }
}
