package the.wuxjian.im.serialize;

import the.wuxjian.im.serialize.impl.JSONSerializer;

/**
 * 序列化和反序列化
 * Created by wuxjian on 2020/9/25
 */
public interface Serializer {
    //默认的序列化方式
    Serializer DEFAULT = new JSONSerializer();

    //序列化算法
    byte getSerializeAlgorithm();

    //序列
    byte[] serialize(Object src);

    //反序列化
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
