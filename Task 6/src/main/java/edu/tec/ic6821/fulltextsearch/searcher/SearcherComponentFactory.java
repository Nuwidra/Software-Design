package edu.tec.ic6821.fulltextsearch.searcher;

import edu.tec.ic6821.fulltextsearch.index.TrieIndex;
import edu.tec.ic6821.fulltextsearch.trie.HashTrie;


import java.io.File;


public final class SearcherComponentFactory {

    private SearcherComponentFactory() {
    }

    public static Searcher searcher(File indexLocation) {
        return new DefaultSearcher(new TrieIndex(indexLocation, new HashTrie<File>()));

    }
}
