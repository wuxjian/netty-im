package the.wuxjian.im.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import the.wuxjian.im.protocol.attribute.Attributes;
import the.wuxjian.im.session.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wuxjian on 2020/9/30
 */
public class SessionUtil {
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.SESSION).get() != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);
    }

    public static void bindGroup(String groupId, ChannelGroup group) {
        groupIdChannelGroupMap.put(groupId, group);
    }

    public static void removeGroup(String groupId) {
        groupIdChannelGroupMap.remove(groupId);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }
}
