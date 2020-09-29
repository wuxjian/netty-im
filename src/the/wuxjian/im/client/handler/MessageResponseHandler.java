package the.wuxjian.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * Created by wuxjian on 2020/9/29
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + packet.getMessage());
    }
}
