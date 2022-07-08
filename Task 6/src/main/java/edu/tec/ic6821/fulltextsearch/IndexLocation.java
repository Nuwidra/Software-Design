package edu.tec.ic6821.fulltextsearch;

import java.io.File;
import java.util.Optional;

public final class IndexLocation {

    public Optional<File> get() {
        final String userHome = System.getProperty("user.home");
        if (!new File(userHome).exists()) {
            return Optional.empty();
        }
        final String path = String.format("%s%s.index", userHome, File.separator);
        return Optional.of(new File(path));
    }

}
