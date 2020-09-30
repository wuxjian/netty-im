package the.wuxjian.im.client.console;

import io.netty.channel.Channel;
import the.wuxjian.im.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * Created by wuxjian on 2020/9/30
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
