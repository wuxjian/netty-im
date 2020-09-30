package the.wuxjian.im.protocol.request;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;

import static the.wuxjian.im.protocol.command.Command.QUIT_GROUP_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
