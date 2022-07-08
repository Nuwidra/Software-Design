package edu.tec.ic6821.fulltextsearch.indexer;

import edu.tec.ic6821.fulltextsearch.index.PersistentIndex;
import edu.tec.ic6821.fulltextsearch.indexer.source.FileSource;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.FileTokenizer;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.FileTokenizerFactory;

import java.io.File;
import java.util.Optional;
import java.util.function.Consumer;

public final class DefaultIndexer implements Indexer {

    private final FileSource fileSource;
    private final FileTokenizerFactory fileTokenizerFactory;
    private final PersistentIndex index;

    public DefaultIndexer(final FileSource fileSource,
                          final FileTokenizerFactory fileTokenizerFactory,
                          final PersistentIndex index) {
        this.fileSource = fileSource;
        this.fileTokenizerFactory = fileTokenizerFactory;
        this.index = index;
    }

    @Override
    public void index(Consumer<File> reporter) {
        while (this.fileSource.hasNext()) {
            final File file = this.fileSource.next();
            reporter.accept(file);

            final Optional<FileTokenizer> optionalFileTokenizer = this.fileTokenizerFactory.fileTokenizer(file);
            if (optionalFileTokenizer.isPresent()) {
                final FileTokenizer fileTokenizer = optionalFileTokenizer.get();
                while (fileTokenizer.hasNext()) {
                    final String word = fileTokenizer.next();
                    this.index.index(word, file);
                }
            }
        }

        this.index.persist();
    }
}
