package the.wuxjian.im.client.console;

import io.netty.channel.Channel;
import the.wuxjian.im.protocol.request.LoginRequestPacket;

import java.util.Scanner;

/**
 * Created by wuxjian on 2020/9/30
 */
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket packet = new LoginRequestPacket();
        System.out.println("输入用户名登录:");
        String name = scanner.next();
        packet.setUsername(name);
        packet.setPassword("123456");

        channel.writeAndFlush(packet);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
