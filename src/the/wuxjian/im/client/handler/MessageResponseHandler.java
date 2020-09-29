package the.wuxjian.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.wuxjian.im.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * Created by wuxjian on 2020/9/29
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    //测试拆包与粘包
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        IntStream.rangeClosed(1, 10000)
//                .boxed().forEach(i -> {
//            MessageRequestPacket packet = new MessageRequestPacket();
//            packet.setMessage("上海市黄浦区河南南路33号12层B室上海市黄浦区河南南路33号12层B室 -> " + i);
//            ctx.channel().writeAndFlush(packet);
//        });
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + packet.getMessage());
    }
}
