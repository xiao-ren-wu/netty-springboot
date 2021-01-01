package org.ywb.netty.common.protocol;

import org.ywb.netty.common.enums.SerializerAlgorithm;
import org.ywb.netty.common.serializer.GsonSerializer;

/**
 * @author yuwenbo1
 * @date 2021/1/1 1:32 下午 星期五
 * @since 1.0.0
 */
public interface Serializer {

    /**
     * 序列化对象
     *
     * @param object obj
     * @return byte[]
     */
    byte[] serialize(Object object);

    /**
     * 反序列化对象
     *
     * @param clazz 对象类型
     * @param bytes obj serial bytes
     * @param <T>   T
     * @return Obj
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    /**
     * 获取序列化算法
     *
     * @return SerializerAlgorithm
     */
    SerializerAlgorithm getSerializerAlgorithm();

    /**
     * 通过序列化算法名称获取序列话算法实例
     *
     * @param serializerAlgorithm {@link SerializerAlgorithm}
     * @return Serializer
     */
    static Serializer getInstance(SerializerAlgorithm serializerAlgorithm) {
        switch (serializerAlgorithm) {
            case JSON:
                return GsonSerializer.INSTANCE;
            default:
                throw new IllegalArgumentException("serializerAlgorithm [" + serializerAlgorithm + "] not support");
        }
    }
}
