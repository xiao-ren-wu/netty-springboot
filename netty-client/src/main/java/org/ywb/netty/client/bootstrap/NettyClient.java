package org.ywb.netty.client.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.ywb.netty.client.config.properties.NettyClientProperties;
import org.ywb.netty.client.console.ConsoleCommandExecManager;
import org.ywb.netty.client.handler.*;
import org.ywb.netty.common.codec.PacketCodeC;
import org.ywb.netty.common.handler.PacketDecodeHandler;
import org.ywb.netty.common.handler.PacketEncodeHandler;
import org.ywb.netty.common.handler.Splitter;
import org.ywb.netty.common.packet.request.MessageRequestPacket;

import javax.annotation.Resource;
import java.util.Scanner;
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

    @Resource
    private ConsoleCommandExecManager consoleCommandExecManager;

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
                                .addLast(new Splitter())
                                .addLast(new PacketDecodeHandler())
                                .addLast(new LoginResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
                                .addLast(new QuitGroupResponseHandler())
                                .addLast(new GroupListResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new PacketEncodeHandler());
                    }
                });

        connect(bootstrap, nettyClientProperties.getHost(), nettyClientProperties.getPort(), 1);
    }


    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("服务端链接成功");
                        log.info("启动控制台线程");
                        startConsoleThread(((ChannelFuture) future).channel());
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

    @SuppressWarnings("all")
    private void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                Scanner scanner = new Scanner(System.in);
                consoleCommandExecManager.execCommand(scanner, channel);
            }
        }).start();
    }

}
