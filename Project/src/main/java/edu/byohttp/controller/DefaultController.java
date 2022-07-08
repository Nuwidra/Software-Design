package edu.byohttp.controller;


import edu.byohttp.httpmethod.HTTPMethod;
import edu.byohttp.log.StdoutLogger;
import edu.byohttp.parser.RequestParser;
import edu.byohttp.request.Request;
import edu.byohttp.response.ErrorResponseFactory;
import edu.byohttp.response.Response;
import edu.byohttp.response.ResponseStatus;

import java.util.Map;
import java.util.Optional;

public final class DefaultController implements Controller {

    private final RequestParser requestParser;
    private final Map<String, HTTPMethod> httpMethods;


    public DefaultController(final RequestParser requestParser, final Map<String, HTTPMethod> httpMethods) {
        this.requestParser = requestParser;
        this.httpMethods = httpMethods;

    }

    @Override
    public Response processInputMessage(String message) {

        final Optional<Request> request = requestParser.buildRequest(message);

        if (request.isEmpty()) {
            return ErrorResponseFactory.generateErrorResponse(ResponseStatus.NOT_IMPLEMENTED);
        }

        final Request obtainedRequest = request.get();
        StdoutLogger.logRequest(obtainedRequest);

        final String methodName = obtainedRequest.getMethod();
        final HTTPMethod method = httpMethods.get(methodName);

        return method == null ? ErrorResponseFactory.generateErrorResponse(ResponseStatus.NOT_IMPLEMENTED):
            method.generateResponse(obtainedRequest);

    }
}
