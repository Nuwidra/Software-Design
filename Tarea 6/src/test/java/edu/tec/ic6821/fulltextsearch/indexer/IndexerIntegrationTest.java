package edu.tec.ic6821.fulltextsearch.indexer;

import edu.tec.ic6821.fulltextsearch.index.TrieIndex;
import edu.tec.ic6821.fulltextsearch.trie.HashTrie;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IndexerIntegrationTest {

    @Test
    public void index() throws URISyntaxException {
        // given
        final Path source = Paths.get(IndexerIntegrationTest.class.getResource("/toindex").toURI());

        final File startingDirectory = source.toFile();
        final File indexLocation = new File("/tmp/.index.IndexerIntegrationTest");
        indexLocation.delete();
        final Indexer indexer = IndexerComponentFactory.indexer(startingDirectory, indexLocation);

        // when
        indexer.index(file -> {

        });

        // then
        assertTrue(indexLocation.exists());
        final TrieIndex index = new TrieIndex(indexLocation, new HashTrie<>());
        index.restore();
        final Set<File> files = index.search("ipsum");
        assertEquals(2, files.size());
    }
}
