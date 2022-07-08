package edu.byohttp.httpmethod;

import edu.byohttp.request.Request;
import edu.byohttp.response.Response;


public interface HTTPMethod {
    Response generateResponse (Request request);
}
