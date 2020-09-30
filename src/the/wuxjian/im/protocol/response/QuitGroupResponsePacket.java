package the.wuxjian.im.protocol.response;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;

import static the.wuxjian.im.protocol.command.Command.QUIT_GROUP_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
