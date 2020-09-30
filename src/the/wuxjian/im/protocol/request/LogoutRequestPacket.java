package the.wuxjian.im.protocol.request;

import the.wuxjian.im.protocol.Packet;
import the.wuxjian.im.protocol.command.Command;

/**
 * Created by wuxjian on 2020/9/30
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
