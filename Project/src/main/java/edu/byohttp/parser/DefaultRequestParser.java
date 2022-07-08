package edu.byohttp.parser;

import edu.byohttp.request.DefaultRequest;
import edu.byohttp.request.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;


public class DefaultRequestParser implements RequestParser {

    public DefaultRequestParser() {
    }


    @Override
    public Optional<Request> buildRequest(String message) {

        final String[] messageParts = message.split("\\R",2);

        final String messageFirstLine = messageParts[0];
        final String headers = messageParts[1];

        final StringTokenizer firstLineTokens = new StringTokenizer(messageFirstLine);

        List<String> firstLineTokensList = new ArrayList<>();
        while (firstLineTokens.hasMoreTokens()) {
            firstLineTokensList.add(firstLineTokens.nextToken());
        }

        final String method = firstLineTokensList.get(0);
        final String resource = firstLineTokensList.get(1);
        final String protocolVersion = firstLineTokensList.get(2);


        if (method.isEmpty() || resource.isEmpty() || Objects.requireNonNull(protocolVersion).isEmpty()) {
            return Optional.empty();
        }

        final Map<String, String> map = generateHeaders(headers);

        Request request = new DefaultRequest(method, resource, protocolVersion, map);

        return Optional.of(request);
    }



    private Map<String, String> generateHeaders(String headers) {

        Map<String, String> map = new HashMap<>();
        final String[] headersParts = headers.split("\\R");

        for (final String actualHeader : headersParts) {
            final String[] headerEntrySet = actualHeader.split(":", 2);

            map.put(headerEntrySet[0], headerEntrySet[1]);
        }

        return map;
    }
}
