package edu.byohttp.request;

public interface Request {
    String getMethod();
    String getResource();
    String getProtocolVersion();
    String getHeaderValue(String headerKey);

    String toString();
}
