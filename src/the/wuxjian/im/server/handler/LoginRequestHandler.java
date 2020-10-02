package the.wuxjian.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.request.LoginRequestPacket;
import the.wuxjian.im.protocol.response.LoginResponsePacket;
import the.wuxjian.im.session.Session;
import the.wuxjian.im.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Created by wuxjian on 2020/9/29
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录……");
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        loginResponsePacket.setUsername(packet.getUsername());
        if (valid(packet)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + packet.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, packet.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        // 登录响应
        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unbindSession(ctx.channel());
    }
}
