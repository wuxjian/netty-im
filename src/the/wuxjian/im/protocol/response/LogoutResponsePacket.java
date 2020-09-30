package the.wuxjian.im.protocol.response;

import lombok.Data;
import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

/**
 * Created by wuxjian on 2020/9/30
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
