package the.wuxjian.im.protocol.command;

import lombok.Data;

/**
 * Created by wuxjian on 2020/9/25
 */
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String username;

    private String password;

    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
