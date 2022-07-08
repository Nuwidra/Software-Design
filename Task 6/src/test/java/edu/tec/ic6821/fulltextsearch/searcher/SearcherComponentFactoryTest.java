package edu.tec.ic6821.fulltextsearch.searcher;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SearcherComponentFactoryTest {

    @Test
    public void testSearcher() {
        // given
        final File indexLocation = new File(".index");

        // when
        final Searcher searcher = SearcherComponentFactory.searcher(indexLocation);

        // then
        assertNotNull(searcher);
        assertTrue(searcher instanceof DefaultSearcher);
    }
}
