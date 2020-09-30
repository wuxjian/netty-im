package the.wuxjian.im.protocol.request;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

import java.util.List;

/**
 * Created by wuxjian on 2020/9/30
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
