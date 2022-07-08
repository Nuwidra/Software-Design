package edu.byohttp.parser;

import edu.byohttp.request.Request;

import java.util.Optional;

public interface RequestParser {
    Optional<Request> buildRequest(String message);


}
