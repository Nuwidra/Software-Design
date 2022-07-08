package edu.tec.ic6821.fulltextsearch.tokenizer;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Optional;
import java.util.Map;

public final class DefaultFileTokenizerFactory implements FileTokenizerFactory {

    private final Map<String, FileTokenizerFactory> tokenizerFactories;

    public DefaultFileTokenizerFactory(final Map<String, FileTokenizerFactory> tokenizerFactories) {
        this.tokenizerFactories = tokenizerFactories;
    }

    @Override
    public Optional<FileTokenizer> fileTokenizer(File file) {
        final String fileExtesion = FilenameUtils.getExtension(file.getName());
        final FileTokenizerFactory defaultFactory = defaultValue -> Optional.empty();
        final FileTokenizerFactory factory = tokenizerFactories.getOrDefault(fileExtesion, defaultFactory);
        return factory.fileTokenizer(file);
    }
}
