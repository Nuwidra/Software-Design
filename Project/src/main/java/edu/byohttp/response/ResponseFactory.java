package edu.byohttp.response;

import java.io.InputStream;
import java.util.Map;

public final class ResponseFactory {
    public static Response response(ResponseStatus responseStatus, String protocolVersion, Map<String,String> headers,
                                    InputStream resourceBytes) {

        return  new DefaultResponse(protocolVersion, headers, resourceBytes, responseStatus);
    }
}
