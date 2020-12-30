package org.ywb.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author yuwenbo1
 * @date 2020/12/30 11:37 下午 星期三
 * @since 1.0.0
 */
@Slf4j
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 这个方法会在客户端连接建立成功之后被调用
     *
     * @param ctx ChannelHandlerContext
     * @throws Exception e
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        log.info("客户端写出数据：[{}]", now);
        ByteBuf byteBuf = genByteBuf(ctx);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("服务端返回数据[{}]", byteBuf.toString(Charset.forName(StandardCharsets.UTF_8.name())));
    }

    private ByteBuf genByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "你好服务端".getBytes(StandardCharsets.UTF_8);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
