package com.app.client.cmd;

import com.app.client.fileTCP.socket.Clientsoket;
import com.app.client.utils.StringUtil;

import java.util.Scanner;

/**
 * @author jon 2021:09:14
 * 命令行工具类
 */


public class CommandTools {



    /**
     * @throws Exception
     */
    public void comd() throws Exception {

        String out = cmdTools();
        if ("".equals(out.trim())) {
            out = cmdTools();
        }
        Clientsoket client = client = new Clientsoket();
        while (true) {
            String[] subOut = out.split("\\s+");
            switch (subOut[0]) {
                case "exit":
                    System.exit(0); //结束进程
                    break;
                case "help":
                    System.out.print(StringUtil.help);//帮助文档
                    out = cmdTools();
                    break;
                case "conn":
                    StringUtil.cmdCode="conn";
                    client.conn(subOut[1], Integer.parseInt(subOut[2]));
                    out = cmdTools();
                    break;
                case "close":
                    StringUtil.cmdCode="close";
                    client.close();
                    out=cmdTools();
                    break;
                case "pass":
                    StringUtil.coder=subOut[1];
                    StringUtil.cmdCode="pass";
                    client.pass();
                    out=cmdTools();
                    break;
                default:
                    System.out.println("输入命令有误: "+subOut[0]);
                    out = cmdTools();
                    break;

            }
        }

    }

    /**
     * @return
     * @throws Exception
     */
    private String cmdTools() throws Exception {
        boolean ident = true;
        int index = -1;
        char[] chars = new char[512];
        System.out.print(StringUtil.cmd);
        do {
            index++;
            chars[index] = (char) System.in.read();
            if (chars[index] == '\n') {
                ident = false;
            }
        } while (ident);
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (c != '\u0000' && c != '\n') {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
