package org.ywb.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ywb.netty.server.config.properties.IdleStateProperties;


/**
 * @author yuwenbo1
 * @date 2021/1/3 1:57 下午 星期日
 * @since 1.0.0
 */
@Component
@ChannelHandler.Sharable
public class ImIdleStateHandler extends IdleStateHandler {

    private final Integer reader;

    @Autowired
    public ImIdleStateHandler(IdleStateProperties idleStateProperties) {
        super(idleStateProperties.getReader(), idleStateProperties.getWriter(), idleStateProperties.getAllTime());
        this.reader = idleStateProperties.getReader();
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(reader + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
