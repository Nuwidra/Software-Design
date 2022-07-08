package edu.byohttp.log;

import edu.byohttp.request.Request;
import edu.byohttp.response.Response;

public final class StdoutLogger {

    public static void logRequest(Request request) {
        System.out.println(request.toString());
    }


    public static void logResponse(Response response) {
        System.out.println(response.toString());
    }
}
