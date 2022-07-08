package edu.tec.ic6821.fulltextsearch.indexer;
import edu.tec.ic6821.fulltextsearch.index.TrieIndex;
import edu.tec.ic6821.fulltextsearch.indexer.source.DirectoryFileSource;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.DefaultFileTokenizerFactory;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.FileTokenizerFactory;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.PdfFileTokenizerFactory;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.TxtFileTokenizerFactory;
import edu.tec.ic6821.fulltextsearch.trie.HashTrie;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public final class IndexerComponentFactory {

    private IndexerComponentFactory() {
    }

    public static Indexer indexer(File startingDirectory, File indexLocation) {

        final Map<String, FileTokenizerFactory> factories = new HashMap<>();
        factories.put("pdf", new PdfFileTokenizerFactory());
        factories.put("txt", new TxtFileTokenizerFactory());

        return new DefaultIndexer(new DirectoryFileSource(startingDirectory),
            new DefaultFileTokenizerFactory(factories),
            new TrieIndex(indexLocation, new HashTrie<>()));
    }
}
