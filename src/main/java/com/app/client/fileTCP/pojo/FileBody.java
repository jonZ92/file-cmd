package com.app.client.fileTCP.pojo;

import java.io.Serializable;

/**
 * @author jon 2021:09:11
 */


public final class FileBody implements Serializable {

    private static final long serialVersionUID = -3396283819418221296L;

    private String fileName;

    private long begin;

    private byte[] bytes;

    private long endl;

    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public long getEndl() {
        return endl;
    }

    public void setEndl(long endl) {
        this.endl = endl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
