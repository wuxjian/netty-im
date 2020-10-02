package the.wuxjian.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.request.LogoutRequestPacket;
import the.wuxjian.im.protocol.response.LogoutResponsePacket;
import the.wuxjian.im.util.SessionUtil;

/**
 * Created by wuxjian on 2020/9/30
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
        LogoutResponsePacket packet = new LogoutResponsePacket();
        packet.setSuccess(true);
        ctx.writeAndFlush(packet);
    }
}
