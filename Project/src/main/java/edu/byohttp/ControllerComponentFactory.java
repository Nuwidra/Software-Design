package edu.byohttp;

import edu.byohttp.controller.Controller;
import edu.byohttp.controller.DefaultController;
import edu.byohttp.httpmethod.GetMethod;
import edu.byohttp.httpmethod.HTTPMethod;
import edu.byohttp.httpmethod.HeadMethod;
import edu.byohttp.parser.DefaultRequestParser;
import edu.byohttp.parser.RequestParser;
import edu.byohttp.resource.DefaultResourceFactory;
import edu.byohttp.resource.DirectoryResourceSearcher;
import edu.byohttp.resource.ResourceFactory;
import edu.byohttp.resource.ResourceSearcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class ControllerComponentFactory {

    public static Controller controller(File mimeTypesMapping, File resourcesDirectory) {
        return new DefaultController(parser(), httpMethods(mimeTypesMapping, resourcesDirectory));
    }

    private static RequestParser parser() {
        return new DefaultRequestParser();
    }

    private static Map<String, HTTPMethod> httpMethods(File mimeTypesMapping, File resourcesDirectory) {

        final Map<String, HTTPMethod> methodsMap = new HashMap<>();
        methodsMap.put("GET", new GetMethod(resourceSearcher(mimeTypesMapping, resourcesDirectory)));
        methodsMap.put("HEAD", new HeadMethod(resourceSearcher(mimeTypesMapping, resourcesDirectory)));
        return methodsMap;
    }

    private static ResourceSearcher resourceSearcher(File mimeTypesMapping, File resourcesDirectory) {
        return new DirectoryResourceSearcher(resourceFactory(), mimeTypesMapping, resourcesDirectory);

    }

    private static ResourceFactory resourceFactory() {
        return new DefaultResourceFactory();

    }


}
