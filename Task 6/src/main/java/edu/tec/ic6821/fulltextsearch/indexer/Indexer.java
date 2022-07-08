package edu.tec.ic6821.fulltextsearch.indexer;

import java.io.File;
import java.util.function.Consumer;

public interface Indexer {
    void index(Consumer<File> reporter);
}
