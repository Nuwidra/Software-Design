package edu.tec.ic6821.fulltextsearch.indexer;

import edu.tec.ic6821.fulltextsearch.indexer.source.DirectoryFileSourceTest;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IndexerComponentFactoryTest {

    @Test
    public void testIndexer() throws URISyntaxException {
        // given
        final File directory = new File(DirectoryFileSourceTest.class.getResource("/filesourcetest").toURI());
        final File indexLocation = new File(".index");

        // when
        final Indexer indexer = IndexerComponentFactory.indexer(directory, indexLocation);

        // then
        assertNotNull(indexer);
        assertTrue(indexer instanceof DefaultIndexer);
    }
}
