package org.ywb.netty.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.ywb.netty.common.enums.Command;
import org.ywb.netty.common.enums.SerializerAlgorithm;
import org.ywb.netty.common.protocol.Packet;
import org.ywb.netty.common.protocol.Serializer;

/**
 * @author yuwenbo1
 * @date 2021/1/1 1:43 下午 星期五
 * @since 1.0.0
 */
public class PacketCodeC {
    /**
     * 版本号
     */
    private static final Byte VERSION = 1;
    /**
     * 魔数
     */
    private static final int MAGIC_NUMBER = 0x12345678;

    public static ByteBuf encode(Packet packet) {
        // 创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        // 获取序列化使用的算法和指令
        SerializerAlgorithm serializerAlgorithm = packet.serializerAlgorithm();
        Command command = packet.command();
        // 序列化对象
        Serializer serializer = Serializer.getInstance(serializerAlgorithm);
        byte[] serializeObj = serializer.serialize(packet);
        // 开始写入byteBuf
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(VERSION);
        byteBuf.writeByte(serializerAlgorithm.getCode());
        byteBuf.writeByte(command.getCode());
        byteBuf.writeInt(serializeObj.length);
        byteBuf.writeBytes(serializeObj);
        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 获取序列化算法
        byte serializerCode = byteBuf.readByte();
        Serializer serializer = Serializer.getInstance(SerializerAlgorithm.of(serializerCode));
        // 获取指令
        byte commandCode = byteBuf.readByte();
        // 获取数据包长度
        int len = byteBuf.readInt();
        byte[] bytes = new byte[len];
        // 读取对象json byte
        byteBuf.readBytes(bytes);
        // 获取对象类型
        Class<? extends Packet> aClass = Command.from(commandCode);
        // 反序列化对象
        return serializer.deserialize(aClass, bytes);
    }

}
