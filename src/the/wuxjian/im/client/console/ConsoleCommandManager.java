package the.wuxjian.im.client.console;

import io.netty.channel.Channel;
import the.wuxjian.im.util.SessionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wuxjian on 2020/9/30
 */
public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("login", new LoginConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("=============请输入指令=============");
        System.out.println("1 login：登录");
        System.out.println("2 logout：登出");
        System.out.println("3 sendToUser：单聊");
        System.out.println("4 createGroup：创建群");
        System.out.println("5 listGroupMembers：查看群成员");
        System.out.println("6 joinGroup：入群");
        //  获取第一个指令
        String command = scanner.next();

        if (!SessionUtil.hasLogin(channel)) {
            if (!"login".equals(command)) {
                System.out.println("请先登录~");
                return;
            }
            ConsoleCommand consoleCommand = consoleCommandMap.get("login");
            consoleCommand.exec(scanner, channel);
            return;
        }

        if ("login".equals(command) && SessionUtil.hasLogin(channel)){
            System.out.println("已登录，无需重复登录");
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
