package edu.byohttp.response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class DefaultResponse implements Response {

    private final String protocolVersion;
    private final Map<String, String> headers;
    private final InputStream resourceBytes;
    private final ResponseStatus responseStatus;


    public DefaultResponse(final String protocolVersion, final Map<String, String> headers, final InputStream resourceBytes,
                           final ResponseStatus responseStatus) {

        this.protocolVersion = protocolVersion;
        this.headers = headers;
        this.resourceBytes = resourceBytes;
        this.responseStatus = responseStatus;
    }

    @Override
    public ResponseStatus getResponseStatus() {
        return this.responseStatus;
    }

    @Override
    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    @Override
    public String getHeaderValue(final String headerKey) {
        return this.headers.get(headerKey);
    }

    @Override
    public InputStream getResourceBytes() {
        return this.resourceBytes;
    }

    @Override
    public String toString() {
        try {
            StringBuilder response = new StringBuilder(this.protocolVersion + " " + this.responseStatus.toString() + "\n");

            for (String headerKey : this.headers.keySet()) {
                response.append(headerKey).append(": ").append(this.getHeaderValue(headerKey)).append("\n");
            }

            response.append(new String(this.resourceBytes.readAllBytes()));
            return response.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}