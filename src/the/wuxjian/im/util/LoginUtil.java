package the.wuxjian.im.util;

import io.netty.channel.Channel;
import the.wuxjian.im.protocol.attribute.Attributes;

/**
 * Created by wuxjian on 2020/9/28
 */
public class LoginUtil {

    //标记channel已登录
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    //判断channel是否登录
    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN).get() != null;
    }

}
