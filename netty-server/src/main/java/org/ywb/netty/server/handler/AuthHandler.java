package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.ywb.netty.common.utils.LoginUtil;

/**
 * @author yuwenbo1
 * @date 2021/1/2 12:26 下午 星期六
 * @since 1.0.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            log.info("用户登录成功！移除用户登录校验器...");
        } else {
            log.info("用户登录失败，强制关闭用户链接...");
        }
    }
}
