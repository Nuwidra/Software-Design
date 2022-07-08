package edu.tec.ic6821.fulltextsearch.indexer.source;

import java.io.File;

public interface FileSource {
    boolean hasNext();
    File next();
}
