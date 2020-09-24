package the.wuxjian.examples.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created by wuxjian on 2020/9/24
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写出数据");

        //1 获取数据
        ByteBuf buf = getByteBuf(ctx);
        //2 写出数据
        ctx.channel().writeAndFlush(buf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer();

        byte[] bytes = "岁月静好".getBytes(StandardCharsets.UTF_8);
        buf.writeBytes(bytes);
        return buf;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + ": 客户端收到服务端消息:" + buf.toString(StandardCharsets.UTF_8));
    }
}
