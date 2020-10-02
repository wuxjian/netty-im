package the.wuxjian.im.protocol.response;

import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
