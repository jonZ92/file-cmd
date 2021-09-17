package com.fileApp.pojo;

import java.io.Serializable;

/**
 * @author jon 2021:09:16
 */


public final class FileBody implements Serializable {

    private static final long serialVersionUID = -3396283819418221296L;

    private String fileName;

    private byte[] bytes;

    private long end;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
