package edu.tec.ic6821.fulltextsearch.indexer.tokenizer;

public interface FileTokenizer {
    boolean hasNext();
    String next();
}
