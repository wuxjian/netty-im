package the.wuxjian.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.request.MessageRequestPacket;
import the.wuxjian.im.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * Created by wuxjian on 2020/9/29
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + packet.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + packet.getMessage() + "】");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
