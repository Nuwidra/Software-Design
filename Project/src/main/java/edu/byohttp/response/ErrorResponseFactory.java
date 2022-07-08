package edu.byohttp.response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ErrorResponseFactory {

    private final static String ERROR_RESPONSE = "<html> \n" +
            "<head><title>%d %s</title></head> \n" +
            "<body> \n" +
            "<center><h1>%d %s</h1></center> \n" +
            "<hr><center>byohttp/0.0.1</center> \n" +
            "</body> \n" +
            "</html> ";



    public static Response generateErrorResponse(ResponseStatus responseStatus){

        return ResponseFactory.response(responseStatus, "HTTP/1.1",
                generateHeaders(), getErrorResourceBytes(responseStatus));
    }

    private static Map<String, String> generateHeaders(){

        return new HashMap<>() {{
            put("Server", "byohttp/0.0.1");
            put("Date", new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss").format(new Date())  + " GMT");
            put("Content-Type","text/html");
            put("Content-Length", String.valueOf(ERROR_RESPONSE.length()));
            put("Connection","keep-alive");
        }};
    }

    private static InputStream getErrorResourceBytes(ResponseStatus responseStatus){

        final String errorMessage = String.format(ERROR_RESPONSE, responseStatus.getCode(), responseStatus.getMessage(),
                responseStatus.getCode(), responseStatus.getMessage());


        return new ByteArrayInputStream(errorMessage.getBytes(StandardCharsets.UTF_8));
    }





}