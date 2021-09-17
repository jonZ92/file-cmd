package com.app.client.fileTCP.handler;


import com.app.client.utils.HandlerUtil;
import com.app.client.utils.StringUtil;
import com.fileApp.pojo.FileBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author jon 2021:09:14
 */


public class ClientFaileHandler extends ChannelInboundHandlerAdapter {

    private FileBody body = new FileBody();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if ("close".equals(StringUtil.CMD_CODE)) {

            ctx.writeAndFlush("close%#%" + StringUtil.CODER);

        } else if ("pass".equals(StringUtil.CMD_CODE)) {

            ctx.writeAndFlush("pass%#%" + StringUtil.CODER);

        } else if ("conn".equals(StringUtil.CMD_CODE)) {

            ctx.writeAndFlush("CMD");

        } else if ("cd".equals(StringUtil.CMD_CODE)) {
            ctx.writeAndFlush("cd%#%" + StringUtil.CODER + "%#%" + StringUtil.PATH_CMD);
        } else if ("upload".equals(StringUtil.CMD_CODE)) {
            FileBody body = new FileBody();
            HandlerUtil.setFileBody(body);
            ctx.writeAndFlush(body);
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String code = (String) msg;
            if ("OK".equals(code)) {
                System.out.println(code + "连接成功......");
                ctx.close();
            } else {
                HandlerUtil.printOut(code);
                ctx.close();
            }
        } else if (msg instanceof Long) {
            if ("upload".equals(StringUtil.CMD_CODE)) {
                long start = (long) msg;
                if (start != -1) {
                    HandlerUtil.start = start;
                    HandlerUtil.setFileBody(body);
                    ctx.writeAndFlush(body);
                }else{
                    body = new FileBody();
                    HandlerUtil.byteRead = 0;
                    HandlerUtil.lastLength = 0x40000;
                    HandlerUtil.start = 0L;
                    ctx.close();
                    System.out.println("文件发送完毕");
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("错误============>" + cause.getMessage());
        ctx.close();
    }

}
