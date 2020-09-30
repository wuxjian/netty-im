package the.wuxjian.im.protocol.attribute;

import io.netty.util.AttributeKey;
import the.wuxjian.im.session.Session;

/**
 * Created by wuxjian on 2020/9/28
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
