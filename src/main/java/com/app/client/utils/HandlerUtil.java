package com.app.client.utils;


import com.fileApp.pojo.FileBody;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author jon 2021:09:15
 */


public final class HandlerUtil {

    private HandlerUtil() {
    }

    public static void printOut(String chars) {

        String[] subChar = chars.split("\\$#\\$");
        if (subChar.length > 1) {
            int index = -1;
            for (String cstr : subChar) {
                index++;

                if (index % 3 != 0 && index != 0) {
                    System.out.print(cstr + "\t\t\t\t");
                } else {
                    System.out.println(cstr);
                }
            }

            System.out.println("");
            System.out.println("");
            StringUtil.CMD_CHAR = "FILE CMD$ " + StringUtil.PATH_CMD + ">";
        } else {
            StringUtil.CMD_CHAR = "FILE CMD$ " + StringUtil.PATH_CMD + ">";
            if ("$#$".equals(chars)) {
            } else {
                System.out.println(chars);
            }
        }

    }

    public static void driveLetter(String[] subStr) {

        boolean isFlag = false;
        if (io.netty.util.internal.StringUtil.isNullOrEmpty(StringUtil.PATH_CMD)) {
            StringUtil.PATH_CMD = subStr[1];
            return;
        } else {
            for (String cStr : StringUtil.DRIVE_LETTER) {
                if (subStr[1].startsWith(cStr)) {
                    isFlag = true;
                }
            }
        }
        if (isFlag) {
            StringUtil.PATH_CMD = subStr[1];
            return;
        } else {
            StringUtil.PATH_CMD = StringUtil.PATH_CMD + subStr[1] + "/";
            return;
        }
    }

    public static volatile int byteRead = 0;
    private static RandomAccessFile randomAccessFile;
    public static int lastLength = 0x40000;
    public static volatile long start = 0L;

    public static void setFileBody( FileBody body) throws Exception {
        File file = new File(StringUtil.FILE_PATH);
        if (io.netty.util.internal.StringUtil.isNullOrEmpty(body.getFileName())) {
            body.setFileName(StringUtil.FILE_PATH.substring(StringUtil.FILE_PATH.lastIndexOf("/") + 1));
        }
        randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.seek(start);
        long nowLength = randomAccessFile.length() - start;
        if (nowLength < lastLength) {
            lastLength = (int) nowLength;
        } else {
            lastLength = 0x40000;
        }
        byte[] bytes = new byte[lastLength];

        if ((byteRead = randomAccessFile.read(bytes)) != -1) {
            body.setBytes(bytes);
            body.setEnd(byteRead);

        }
        randomAccessFile.close();
    }


    private void speedOfProgress(long fileLength, long nowLength, boolean isflag) {

        long length = (long) 0x40000;
        if (isflag) {

            if (length >= fileLength) {
                //log.info("文件已经发送: 100%");
            } else {
                double is = (double) length / fileLength;
                //log.info("文件已经发送: {}", new DecimalFormat("#.##%").format(is));
            }
        } else {
            double is = (double) nowLength / fileLength;
            //log.info("文件已经发送: {}", new DecimalFormat("#.##%").format(is));
        }

    }

}
