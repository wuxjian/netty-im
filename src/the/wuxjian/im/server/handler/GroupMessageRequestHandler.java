package the.wuxjian.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import the.wuxjian.im.protocol.request.GroupMessageRequestPacket;
import the.wuxjian.im.protocol.response.GroupMessageResponsePacket;
import the.wuxjian.im.util.SessionUtil;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = msg.getToGroupId();
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(msg.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);

    }
}
