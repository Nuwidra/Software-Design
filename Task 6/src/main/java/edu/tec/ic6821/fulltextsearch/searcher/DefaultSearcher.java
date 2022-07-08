package edu.tec.ic6821.fulltextsearch.searcher;

import edu.tec.ic6821.fulltextsearch.index.PersistentIndex;

import java.io.File;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public final class DefaultSearcher implements Searcher {

    private final PersistentIndex index;

    public DefaultSearcher(final PersistentIndex index) {
        this.index = index;
        this.index.restore();
    }

    @Override
    public Set<File> search(Set<String> terms) {
        final Optional<Set<File>> resultSets = terms.stream()
            .map(this.index::search)
            .reduce((resultSet1, resultSet2) -> resultSet1.retainAll(resultSet2) ? resultSet1 : resultSet1);

        return resultSets.orElse(Collections.emptySet());
    }
}
