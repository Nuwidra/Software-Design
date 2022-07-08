package edu.byohttp.request;

import java.util.Map;

public final class DefaultRequest implements Request {

    private final String method;
    private final String resource;
    private final String protocolVersion;
    private final Map<String, String> headers;

    public DefaultRequest(final String method, final String resource, final String protocolVersion,
                          final Map<String, String> headers) {
        this.method = method;
        this.resource = resource;
        this.protocolVersion = protocolVersion;
        this.headers = headers;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getResource() {
        return this.resource;
    }

    @Override
    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    @Override
    public String getHeaderValue(String headerKey) {
        return this.headers.get(headerKey);
    }

    @Override
    public String toString() {

        StringBuilder request = new StringBuilder(this.method + " " +  this.resource + " " + this.protocolVersion + "\n");

        for (String headerKey : this.headers.keySet()) {
            request.append(headerKey).append(": ").append(this.getHeaderValue(headerKey)).append("\n");
        }

        return request.toString();
    }
}
