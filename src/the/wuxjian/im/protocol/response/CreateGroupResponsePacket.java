package the.wuxjian.im.protocol.response;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

import java.util.List;

/**
 * Created by wuxjian on 2020/9/30
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
