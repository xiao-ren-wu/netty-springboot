package org.ywb.netty.server.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.ywb.netty.server.config.properties.NettyServerProperties;
import org.ywb.netty.server.handler.FirstServerHandler;

import javax.annotation.Resource;

/**
 * @author yuwenbo1
 * @date 2020/12/30 20:29
 * @since 1.0.0
 */
@Slf4j
@Component
public class NettyServer implements CommandLineRunner {

    @Resource
    private NettyServerProperties nettyServerProperties;

    @Override
    public void run(String... args) throws Exception {

        Integer port = nettyServerProperties.getPort();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                })
                .bind(port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("netty server use port {} start success.", port);
                    } else {
                        log.warn("port {} already in use,will use {}", port, port + 1);
                        serverBootstrap.bind(port + 1);
                    }
                });
    }
}
