package edu.tec.ic6821.fulltextsearch.indexer.tokenizer;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public final class DefaultFileTokenizerFactory implements FileTokenizerFactory {

    private final Map<String, FileTokenizerFactory> factories;

    public DefaultFileTokenizerFactory(final Map<String, FileTokenizerFactory> factories) {
        this.factories = factories;
    }

    @Override
    public Optional<FileTokenizer> fileTokenizer(File file) {
        final String extension = FilenameUtils.getExtension(file.getName());
        final FileTokenizerFactory factory = this.factories.getOrDefault(extension, (File f) -> Optional.empty());

        return factory.fileTokenizer(file);
    }
}
