package the.wuxjian.im.protocol.request;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;

import static the.wuxjian.im.protocol.command.Command.JOIN_GROUP_REQUEST;

@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}
