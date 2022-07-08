package edu.byohttp.resource;


import java.util.Optional;

public interface ResourceSearcher {

    Optional<Resource> findResource(String resource);

}
