package the.wuxjian.im.protocol.request;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

/**
 * Created by wuxjian on 2020/9/28
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
