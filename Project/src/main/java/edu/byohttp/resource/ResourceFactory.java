package edu.byohttp.resource;

import java.io.InputStream;

public interface ResourceFactory {

    Resource resource(String fileName, String mimeType, Long bytesSize, String lastModified, InputStream bytes);
}
