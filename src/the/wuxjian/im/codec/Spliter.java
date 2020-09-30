package the.wuxjian.im.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import the.wuxjian.im.protocol.PacketCodec;

/**
 * Created by wuxjian on 2020/9/29
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.readableBytes() >=4 && in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            System.out.println("非本协议， 连接关闭");
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
