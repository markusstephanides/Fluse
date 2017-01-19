package com.digotsoft.fluse.server.files;

/**
 * @author Digot
 * @version 1.0
 */
public class FileInformation {

    private byte[] data;
    private String mimeType;

    public FileInformation( byte[] data, String mimeType ) {
        this.data = data;
        this.mimeType = mimeType;
    }

    public byte[] getData() {
        return data;
    }

    public String getMimeType() {
        return mimeType;
    }
}
