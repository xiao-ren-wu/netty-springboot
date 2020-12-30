package org.ywb.netty.client.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.ywb.netty.client.config.properties.NettyClientProperties;
import org.ywb.netty.client.handler.FirstClientHandler;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yuwenbo1
 * @date 2020/12/30 21:03
 * @since 1.0.0
 */
@Slf4j
@Component
public class NettyClient implements CommandLineRunner {

    @Resource
    private NettyClientProperties nettyClientProperties;

    @Override
    public void run(String... args) throws Exception {

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new FirstClientHandler());
                    }
                });

        connect(bootstrap, nettyClientProperties.getHost(), nettyClientProperties.getPort(), 1);
    }


    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("服务端链接成功");
                    } else if (retry > nettyClientProperties.getMaxRetry()) {
                        log.error("重试次数已经用完");
                        System.exit(0);
                    } else {
                        log.warn("服务端链接失败，即将准备第{}次重连", retry);
                        int delay = 1 << retry;
                        bootstrap.config()
                                .group()
                                .schedule(() -> connect(bootstrap, host, port, retry + 1), delay, TimeUnit.SECONDS);
                    }
                });
    }

}
