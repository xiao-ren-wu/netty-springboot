package org.ywb.netty.server.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuwenbo1
 * @date 2020/12/30 21:38
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty-server")
public class NettyServerProperties {
    /**
     * netty服务端端口
     */
    private Integer port;
}
