package the.wuxjian.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import the.wuxjian.im.client.handler.LoginResponseHandler;
import the.wuxjian.im.client.handler.LogoutResponseHandler;
import the.wuxjian.im.client.handler.MessageResponseHandler;
import the.wuxjian.im.codec.PacketDecoder;
import the.wuxjian.im.codec.PacketEncoder;
import the.wuxjian.im.codec.Spliter;
import the.wuxjian.im.protocol.request.LoginRequestPacket;
import the.wuxjian.im.protocol.request.MessageRequestPacket;
import the.wuxjian.im.util.SessionUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuxjian on 2020/9/28
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new Spliter());
                        pipeline.addLast(new PacketDecoder());
                        pipeline.addLast(new PacketEncoder());
                        pipeline.addLast(new LoginResponseHandler());
                        pipeline.addLast(new LogoutResponseHandler());
                        pipeline.addLast(new MessageResponseHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功!");

                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);

            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String username = sc.nextLine();
                    loginRequestPacket.setUsername(username);

                    // 密码使用默认的
                    loginRequestPacket.setPassword("pwd");

                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                } else {
                    System.out.println("输入userId和要发送的消息: ");
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                    System.out.println("发送成功");
                    System.out.println();
                }
            }
        }).start();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
