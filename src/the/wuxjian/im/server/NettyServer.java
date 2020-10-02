package the.wuxjian.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import the.wuxjian.im.codec.PacketCodecHandler;
import the.wuxjian.im.codec.PacketDecoder;
import the.wuxjian.im.codec.PacketEncoder;
import the.wuxjian.im.codec.Spliter;
import the.wuxjian.im.handler.IMIdleStateHandler;
import the.wuxjian.im.server.handler.*;

import java.util.Date;

/**
 * Created by wuxjian on 2020/9/28
 */
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        //空闲检测
                        pipeline.addLast(new IMIdleStateHandler());
                        //拆包粘包
                        pipeline.addLast(new Spliter());
                        //编解码
                        pipeline.addLast(PacketCodecHandler.INSTANCE);
                        //登录请求
                        pipeline.addLast(LoginRequestHandler.INSTANCE);
                        //身份校验
                        pipeline.addLast(AuthHandler.INSTANCE);
                        //通用的处理器
                        pipeline.addLast(IMHandler.INSTANCE);
                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
