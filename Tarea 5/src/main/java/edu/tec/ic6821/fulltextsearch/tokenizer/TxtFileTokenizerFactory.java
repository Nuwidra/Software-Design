package edu.tec.ic6821.fulltextsearch.tokenizer;
import java.io.File;
import java.util.Optional;

public final class TxtFileTokenizerFactory implements FileTokenizerFactory {

    @Override
    public Optional<FileTokenizer> fileTokenizer(File file) {
        return Optional.of(new TxtFileTokenizer(file));
    }
}
