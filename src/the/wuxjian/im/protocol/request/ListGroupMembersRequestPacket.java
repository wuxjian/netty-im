package the.wuxjian.im.protocol.request;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;

import static the.wuxjian.im.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
