package com.app.client.fileTCP.handler;

import com.app.client.fileTCP.pojo.FilePojo;
import com.app.client.utils.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

/**
 * @author jon 2021:09:14
 */


public class ClientFaileHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if ("close".equals(StringUtil.cmdCode)) {
            ctx.writeAndFlush("close:" + StringUtil.coder);
            //ctx.close();
        } else if ("pass".equals(StringUtil.cmdCode)) {
            ctx.writeAndFlush("pass:" + StringUtil.coder);
            //ctx.close();
        }else if("conn".equals(StringUtil.cmdCode)){
            ctx.writeAndFlush("CMD");
        }


    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String code = (String) msg;
            if ("OK".equals(code)) {
                //System.out.println("连接成功");
                ctx.close();
            } else {
                System.out.println(code);
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //log.error("错误============>:{}", cause.getMessage());
        ctx.close();
    }


}
