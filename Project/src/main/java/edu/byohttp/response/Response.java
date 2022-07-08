package edu.byohttp.response;

import java.io.InputStream;

public interface Response {
    ResponseStatus getResponseStatus();
    String getProtocolVersion();
    String getHeaderValue(String headerKey);
    InputStream getResourceBytes();

    String toString();
}
