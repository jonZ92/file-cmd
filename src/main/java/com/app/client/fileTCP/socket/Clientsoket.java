package com.app.client.fileTCP.socket;

import com.app.client.fileTCP.handler.ClientFaileHandler;
import com.app.client.utils.StringUtil;
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


    public void conn(String[] address) {

        if (address.length != 3) {
            System.out.println("输入的ip地址或端口有误");
            return;
        }
        this.host = address[1];

        this.port = Integer.parseInt(address[2]);

        boolean b1 = host.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        if (!b1) {
            System.out.println("输入的ip地址有误=> " + host);
        }

        if (1024 > port || port > 65535) {
            System.out.println("输入的端口地址有误=> " + port);
        }

        connect();
    }

    public void dwLoad(String path) {

    }

    public void upLoad(String [] subOut) {
        if(subOut.length!=2){
            System.out.println("需要上传文件路径不能为空");
            return;
        }
        StringUtil.FILE_PATH=subOut[1];
        connect();
    }

    public void close() {
        StringUtil.PATH_CMD = "";
        connect();
    }

    public void pass() {
        connect();
    }

    public void cdCmd() {
        connect();
    }


    public void connect() {
        if (port == null) {
            System.out.println("请先连接服务");
            return;
        }
        if (io.netty.util.internal.StringUtil.isNullOrEmpty(host)) {
            System.out.println("请先连接服务");
            return;
        }


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
            System.out.println("运行出错:" + e.getMessage());

        } finally {

            group.shutdownGracefully();

            try {
                TimeUnit.SECONDS.sleep(1);//休眠10s

                //executor.execute(()->connect());
            } catch (Exception ex) {

            }

        }

    }

}
