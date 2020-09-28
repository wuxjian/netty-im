package the.wuxjian.im.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import the.wuxjian.im.protocol.command.Command;
import the.wuxjian.im.protocol.request.LoginRequestPacket;
import the.wuxjian.im.protocol.response.LoginResponsePacket;
import the.wuxjian.im.serialize.Serializer;
import the.wuxjian.im.serialize.SerializerAlgorithm;
import the.wuxjian.im.serialize.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxjian on 2020/9/25
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;


    public final static PacketCodeC INSTANCE = new PacketCodeC();

    private PacketCodeC() {}


    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializeAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        ByteBuf buffer = byteBufAllocator.ioBuffer();
        //1 魔数
        buffer.writeInt(MAGIC_NUMBER);
        //2 版本号
        buffer.writeByte(packet.getVersion());
        //3 序列化算法
        buffer.writeByte(SerializerAlgorithm.JSON);

        //4 命令
        buffer.writeByte(packet.getCommand());
        //5 长度
        buffer.writeInt(bytes.length);
        //6 内容
        buffer.writeBytes(bytes);

        return buffer;
    }

    public Packet decode(ByteBuf buf) {
        //跳过魔数
        buf.skipBytes(4);
        //跳过版本号
        buf.skipBytes(1);

        //序列化算法
        byte serializerAlgorithm = buf.readByte();

        //command
        byte command = buf.readByte();

        //length
        int length = buf.readInt();

        byte[] dst = new byte[length];
        buf.readBytes(dst);

        Serializer serializer = getSerializer(serializerAlgorithm);
        Class<? extends Packet> clazz = getRequestType(command);
        return serializer.deserialize(clazz, dst);
    }


    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

    public static void main(String[] args) {
        PacketCodeC codeC = new PacketCodeC();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("wuxjian");
        loginRequestPacket.setPassword("123456");
        loginRequestPacket.setUserId("10000");
        ByteBuf buf = codeC.encode(ByteBufAllocator.DEFAULT, loginRequestPacket);

        Packet packet = codeC.decode(buf);

        System.out.println(packet);
    }
}
