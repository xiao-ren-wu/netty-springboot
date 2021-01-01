package org.ywb.netty.common.serializer;

import com.google.gson.Gson;
import org.ywb.netty.common.enums.SerializerAlgorithm;
import org.ywb.netty.common.protocol.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * @author yuwenbo1
 * @date 2021/1/1 1:36 下午 星期五
 * @since 1.0.0
 * json 序列化实现
 */
public class GsonSerializer implements Serializer {

    public static final Serializer INSTANCE = new GsonSerializer();

    private static final Gson GSON = new Gson();

    @Override
    public byte[] serialize(Object object) {
        return GSON.toJson(object).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return GSON.fromJson(new String(bytes, StandardCharsets.UTF_8), clazz);
    }

    @Override
    public SerializerAlgorithm getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }
}
