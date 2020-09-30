package the.wuxjian.im.protocol.response;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.session.Session;

import java.util.List;

import static the.wuxjian.im.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
