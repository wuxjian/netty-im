package the.wuxjian.im.client.console;

import io.netty.channel.Channel;
import the.wuxjian.im.protocol.request.CreateGroupRequestPacket;
import the.wuxjian.im.session.Session;
import the.wuxjian.im.util.SessionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by wuxjian on 2020/9/30
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
        System.out.println("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        List<String> userIdList = Arrays.stream(userIds.split(",")).collect(Collectors.toList());
        Session session = SessionUtil.getSession(channel);
        userIdList.add(0, session.getUserId());
        packet.setUserIdList(userIdList);
        channel.writeAndFlush(packet);
    }
}
