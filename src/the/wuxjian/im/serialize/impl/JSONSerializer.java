package the.wuxjian.im.serialize.impl;

import com.alibaba.fastjson.JSON;
import the.wuxjian.im.serialize.Serializer;
import the.wuxjian.im.serialize.SerializerAlgorithm;

/**
 * Created by wuxjian on 2020/9/25
 */
public class JSONSerializer implements Serializer {
    public byte getSerializeAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object src) {
        return JSON.toJSONBytes(src);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
