package org.ywb.netty.client.console.annos;

import org.springframework.stereotype.Component;
import org.ywb.netty.client.enums.ConsoleCommand;

import java.lang.annotation.*;

/**
 * @author yuwenbo1
 * @date 2021/1/2 3:08 下午 星期六
 * @since 1.0.0
 */
@Component
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Console {
    /**
     * 指令
     *
     * @return ConsoleCommand
     */
    ConsoleCommand command();
}
