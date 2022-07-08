package edu.byohttp.httpmethod;

import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;
import edu.byohttp.response.ErrorResponseFactory;
import edu.byohttp.response.Response;
import edu.byohttp.resource.ResourceSearcher;
import edu.byohttp.response.ResponseFactory;
import edu.byohttp.response.ResponseStatus;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class HeadMethod implements HTTPMethod {

    private final ResourceSearcher resourceSearcher;

    public HeadMethod(final ResourceSearcher resourceSearcher){
        this.resourceSearcher = resourceSearcher;
    }

    @Override
    public Response generateResponse(Request request) {
        final Optional<Resource> resource = resourceSearcher.findResource(request.getResource());

        if (resource.isEmpty()) {
            return ErrorResponseFactory.generateErrorResponse(ResponseStatus.NOT_FOUND);
        }

        final Resource obtainedResource = resource.get();

        Map<String, String> responseHeaders = new HashMap<>(){{
            put("Server", "byohttp/0.0.1");
            put("Date", DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss") + "GMT");
            put("Content-Type", obtainedResource.getMimeType());
            put("Content-Length", String.valueOf(obtainedResource.getBytesSize()));
            put("Last-Modified", obtainedResource.getLastModified());
            put("Connection", "keep-alive");
            put("Accept-Ranges", "bytes");
        }};

        return ResponseFactory.response(ResponseStatus.OK, request.getProtocolVersion(), responseHeaders, InputStream.nullInputStream());
    }
}
