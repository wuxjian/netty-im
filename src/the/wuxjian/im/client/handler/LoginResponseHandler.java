package the.wuxjian.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.response.LoginResponsePacket;
import the.wuxjian.im.session.Session;
import the.wuxjian.im.util.SessionUtil;

/**
 * Created by wuxjian on 2020/9/29
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        String userId = packet.getUserId();
        String username = packet.getUsername();

        if (packet.isSuccess()) {
            System.out.println("[" + username + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        } else {
            System.out.println("[" + username + "]登录失败，原因：" + packet.getReason());
        }
    }
}
