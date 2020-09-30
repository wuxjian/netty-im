package the.wuxjian.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by wuxjian on 2020/9/30
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
