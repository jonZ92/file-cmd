package com.app.client.cmd;

import com.app.client.fileTCP.socket.Clientsoket;
import com.app.client.utils.HandlerUtil;
import com.app.client.utils.StringUtil;

import java.util.Scanner;

/**
 * @author jon 2021:09:14
 * 命令行工具类
 */


public final class CommandTools {


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
                    System.out.println(StringUtil.HELP);//帮助文档
                    System.out.println("");
                    System.out.println("");
                    out = cmdTools();
                    break;
                case "conn":
                    StringUtil.CMD_CODE = "conn";
                    client.conn(subOut);
                    out = cmdTools();
                    break;
                case "close":
                    StringUtil.CMD_CODE = "close";
                    client.close();
                    out = cmdTools();
                    break;
                case "pass":
                    StringUtil.CODER = subOut[1];
                    StringUtil.CMD_CODE = "pass";
                    client.pass();
                    out = cmdTools();
                    break;
                case "cd":
                    HandlerUtil.driveLetter(subOut);
                    StringUtil.CMD_CODE = "cd";
                    client.cdCmd();
                    out = cmdTools();
                    break;
                case "upload":
                    StringUtil.CMD_CODE = "upload";
                    client.upLoad(subOut);
                    out = cmdTools();
                    break;
                case "":
                    out = cmdTools();
                    break;
                default:
                    System.out.println("输入命令有误: " + subOut[0]);
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
        System.out.print(StringUtil.CMD_CHAR);
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
