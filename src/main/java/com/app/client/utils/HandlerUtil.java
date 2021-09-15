package com.app.client.utils;

/**
 * @author jon 2021:09:15
 */


public final class HandlerUtil {

    private HandlerUtil(){}

    public static void printOut(String chars){

        String [] subChar=chars.split("\\$#\\$");
        if(subChar.length>1){
            int index=-1;
            for(String cstr:subChar){
                index++;
                if(index%3!=0&&index!=0){
                    System.out.print(cstr+"\t\t\t\t");
                }else{
                    System.out.println(cstr);
                }
            }

            System.out.println("");
            System.out.println("");
            StringUtil.CMD_CHAR="FILE CMD$ "+StringUtil.PATH_CMD+ ">";
        }else{
            System.out.println(chars);
        }

    }

}
