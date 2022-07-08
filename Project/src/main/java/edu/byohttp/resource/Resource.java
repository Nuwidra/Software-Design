package edu.byohttp.resource;

import java.io.InputStream;

public final class Resource {

    private final String fileName;
    private final String mimeType;
    private final Long bytesSize;
    private final String lastModified;
    private final InputStream bytes;

    public Resource(final String fileName, final String mimeType, final Long bytesSize,
                    final String lastModified, final InputStream bytes) {

        this.fileName = fileName;
        this.mimeType = mimeType;
        this.bytesSize = bytesSize;
        this.lastModified = lastModified;
        this.bytes = bytes;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public Long getBytesSize() {
        return this.bytesSize;
    }

    public InputStream getBytes() {
        return this.bytes;
    }
}
