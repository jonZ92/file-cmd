package com.app.client.fileTCP.socket;

import com.app.client.fileTCP.handler.ClientFaileHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @author jon 2021:09:14
 */


public class Clientsoket {
    private String host;

    private Integer port;


    public void conn(String host, Integer port) {
        boolean b1 = host.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        if (!b1) {
            System.out.println("输入的ip地址有误=> " + host);
        }

        if (1024 > port || port > 65535) {
            System.out.println("输入的端口地址有误=> " + port);
        }
        this.host = host;
        this.port = port;

        connect();
        System.out.println("连接成功");
    }

    public void dwLoad(String path) {

    }

    public void upLoad(String path) {

    }

    public void close() {
        connect();
    }

    public void pass() {
        connect();
    }


    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bt = new Bootstrap();
            bt.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
                            pipeline.addLast("clientHandler", new ClientFaileHandler());
                        }
                    });
            ChannelFuture sync = bt.connect(host, port).sync();
            //log.info("客户端启动成功 HOST=>:{},PORT=>:{}", host, port);
            sync.channel().closeFuture().sync();

        } catch (Exception e) {

            //log.error("运行出错:{}", e.getMessage());

        } finally {

            group.shutdownGracefully();

            //log.info("客户端已关闭");

            try {
                TimeUnit.SECONDS.sleep(1);//休眠10s
                // log.info("服务运行线程: {}", Thread.currentThread().getName());

                //executor.execute(()->connect());
            } catch (Exception ex) {

            }

        }

    }

}
