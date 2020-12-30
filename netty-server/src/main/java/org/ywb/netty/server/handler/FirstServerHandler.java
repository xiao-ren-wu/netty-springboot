package org.ywb.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


/**
 * @author yuwenbo1
 * @date 2020/12/30 11:46 下午 星期三
 * @since 1.0.0
 */
@Slf4j
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("客户端发送数据[{}]", byteBuf.toString(Charset.forName(StandardCharsets.UTF_8.name())));
        ctx.writeAndFlush(genByteBuf(ctx));
    }

    private ByteBuf genByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("你好客户端".getBytes(Charset.forName(StandardCharsets.UTF_8.name())));
        return byteBuf;
    }
}
