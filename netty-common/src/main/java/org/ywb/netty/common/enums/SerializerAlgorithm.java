package org.ywb.netty.common.enums;

import lombok.Getter;

/**
 * @author yuwenbo1
 * @date 2021/1/1 1:33 下午 星期五
 * @since 1.0.0
 */
@Getter
public enum SerializerAlgorithm {
    /**
     * JSON序列化
     */
    JSON(Byte.parseByte("1")),
    ;

    Byte code;

    SerializerAlgorithm(Byte code) {
        this.code = code;
    }

    public static SerializerAlgorithm of(byte code) {
        switch (code){
            case 1:
                return JSON;
            default:
                throw new IllegalArgumentException("Serializer code ["+code+"] not found suitable SerializerAlgorithm");
        }
    }
}
