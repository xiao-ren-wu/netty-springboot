package org.ywb.netty.client.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuwenbo1
 * @date 2020/12/30 21:48
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty-client")
public class NettyClientProperties {
    /**
     * 服务端端口
     */
    private Integer port;
    /**
     * 服务端host
     */
    private String host;

    /**
     * 重连次数
     */
    private Integer retry;
}
