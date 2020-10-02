package the.wuxjian.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.wuxjian.im.util.SessionUtil;

/**
 * 身份校验
 * Created by wuxjian on 2020/9/29
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler(){}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            //关闭通过
            ctx.channel().close();
        }else {
            //认证成功
            //从pipeline中移除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
