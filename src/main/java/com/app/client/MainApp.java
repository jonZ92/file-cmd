package com.app.client;

import com.app.client.cmd.CommandTools;
import com.app.client.utils.StringUtil;

/**
 * @author jon 2021:09:14
 */


public class MainApp {


    public static void main(String[] args) throws Exception{
        System.out.println(StringUtil.ascii_name);
        CommandTools tools=new CommandTools();
        tools.comd();
    }
}
