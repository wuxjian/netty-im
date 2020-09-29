package the.wuxjian.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.request.LoginRequestPacket;
import the.wuxjian.im.protocol.response.LoginResponsePacket;
import the.wuxjian.im.util.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Created by wuxjian on 2020/9/29
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("wuxjian");
        loginRequestPacket.setPassword("123456");

        //写入
        ctx.channel().writeAndFlush(loginRequestPacket);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        if (packet.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + packet.getReason());
        }
    }
}
