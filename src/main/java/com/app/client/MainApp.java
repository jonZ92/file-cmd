package com.app.client;

import com.app.client.cmd.CommandTools;
import com.app.client.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * @author jon 2021:09:14
 */


public class MainApp {

    private static final Logger log = LogManager.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception{
        log.info(StringUtil.ascii_name);
        CommandTools tools=new CommandTools();
        tools.comd();
    }
}
