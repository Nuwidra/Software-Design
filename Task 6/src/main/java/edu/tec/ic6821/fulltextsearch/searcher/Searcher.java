package edu.tec.ic6821.fulltextsearch.searcher;

import java.io.File;
import java.util.Set;

public interface Searcher {
    Set<File> search(Set<String> terms);
}
