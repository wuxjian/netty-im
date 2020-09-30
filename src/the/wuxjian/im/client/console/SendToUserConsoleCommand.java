package the.wuxjian.im.client.console;

import io.netty.channel.Channel;
import the.wuxjian.im.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * Created by wuxjian on 2020/9/30
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
