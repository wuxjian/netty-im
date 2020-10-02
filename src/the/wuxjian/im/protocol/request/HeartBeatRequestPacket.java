package the.wuxjian.im.protocol.request;

import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
