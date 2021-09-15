package com.app.client.fileTCP.handler;

import com.app.client.utils.HandlerUtil;
import com.app.client.utils.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author jon 2021:09:14
 */


public class ClientFaileHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if ("close".equals(StringUtil.CMD_CODE)) {

            ctx.writeAndFlush("close%#%" + StringUtil.CODER);

        } else if ("pass".equals(StringUtil.CMD_CODE)) {

            ctx.writeAndFlush("pass%#%" + StringUtil.CODER);

        } else if ("conn".equals(StringUtil.CMD_CODE)) {

            ctx.writeAndFlush("CMD");

        }else if("cd".equals(StringUtil.CMD_CODE)){
            ctx.writeAndFlush("cd%#%"+StringUtil.CODER+"%#%"+StringUtil.PATH_CMD);
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String code = (String) msg;
            if ("OK".equals(code)) {
                ctx.close();
            } else {
                HandlerUtil.printOut(code);
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("错误============>"+cause.getMessage());
        ctx.close();
    }
}
