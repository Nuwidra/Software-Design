package edu.byohttp.resource;

import java.io.InputStream;

public final class DefaultResourceFactory implements ResourceFactory {

    public DefaultResourceFactory() {
    }

    @Override
    public Resource resource(String fileName, String mimeType, Long bytesSize,
                             String lastModified, InputStream bytes) {

        return new Resource(fileName, mimeType, bytesSize, lastModified, bytes);
    }


}
