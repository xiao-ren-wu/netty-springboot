package org.ywb.netty.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuwenbo1
 * @date 2021/1/3 2:12 下午 星期日
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty-server.idle-state")
public class IdleStateProperties {
    private Integer reader;
    private Integer writer;
    private Integer allTime;
}
