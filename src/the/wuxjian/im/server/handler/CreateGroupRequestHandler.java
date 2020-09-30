package the.wuxjian.im.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import the.wuxjian.im.protocol.request.CreateGroupRequestPacket;
import the.wuxjian.im.protocol.response.CreateGroupResponsePacket;
import the.wuxjian.im.session.IDUtil;
import the.wuxjian.im.session.Session;
import the.wuxjian.im.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxjian on 2020/9/30
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        CreateGroupResponsePacket response = new CreateGroupResponsePacket();
        List<String> userIdList = msg.getUserIdList();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();

        userIdList.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                Session session = SessionUtil.getSession(channel);
                userNameList.add(session.getUsername());
            }
        });

        //创建群的响应
        response.setSuccess(true);
        response.setUserNameList(userNameList);
        response.setGroupId(IDUtil.randomId());

        //给群里的每个成员发送通知
        channelGroup.writeAndFlush(response);

        System.out.print("群创建成功，id 为[" + response.getGroupId() + "], ");
        System.out.println("群里面有：" + response.getUserNameList());
    }
}
