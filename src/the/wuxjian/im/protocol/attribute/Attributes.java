package the.wuxjian.im.protocol.attribute;

import io.netty.util.AttributeKey;

/**
 * Created by wuxjian on 2020/9/28
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
