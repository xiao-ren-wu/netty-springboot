package org.ywb.netty.server.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.handler.PacketDecodeHandler;
import org.ywb.netty.common.handler.PacketEncodeHandler;
import org.ywb.netty.common.handler.Splitter;
import org.ywb.netty.server.config.properties.NettyServerProperties;
import org.ywb.netty.server.handler.*;

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

    @Resource
    private ImIdleStateHandler imIdleStateHandler;

    @Resource
    private LoginRequestHandler loginRequestHandler;

    @Resource
    private AuthHandler authHandler;

    @Resource
    private CreateGroupRequestHandler createGroupRequestHandler;

    @Resource
    private JoinGroupRequestHandler joinGroupRequestHandler;

    @Resource
    private QuitGroupRequestHandler quitGroupRequestHandler;

    @Resource
    private GroupListRequestHandler groupListRequestHandler;

    @Resource
    private SendToGroupRequestHandler sendToGroupRequestHandler;

    @Resource
    private MessageRequestHandler messageRequestHandler;

    @Resource
    private HeartbeatRequestHandler heartbeatRequestHandler;

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
                        ch.pipeline()
                                .addLast(imIdleStateHandler)
                                .addLast(new Splitter())
                                .addLast(new PacketDecodeHandler())
                                .addLast(loginRequestHandler)
                                .addLast(authHandler)
                                .addLast(heartbeatRequestHandler)
                                .addLast(createGroupRequestHandler)
                                .addLast(joinGroupRequestHandler)
                                .addLast(quitGroupRequestHandler)
                                .addLast(groupListRequestHandler)
                                .addLast(sendToGroupRequestHandler)
                                .addLast(messageRequestHandler)
                                .addLast(new PacketEncodeHandler());
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
