package the.wuxjian.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.response.LogoutResponsePacket;
import the.wuxjian.im.util.SessionUtil;

/**
 * Created by wuxjian on 2020/9/30
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        System.out.println("客户端退出成功");
    }
}
