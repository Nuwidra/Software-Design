package edu.tec.ic6821.fulltextsearch.searcher;

import edu.tec.ic6821.fulltextsearch.index.PersistentIndex;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class DefaultSearcherTest {

    @Test
    public void testSearch() {
        // given
        final File file1 = new File(".file1");
        final File file2 = new File(".file2");
        final File file3 = new File(".file3");
        final File file4 = new File(".file4");

        final PersistentIndex index = Mockito.mock(PersistentIndex.class);
        when(index.search("abc")).thenReturn(new HashSet<>(Arrays.asList(file1, file2, file4)));
        when(index.search("def")).thenReturn(new HashSet<>(Arrays.asList(file2, file3, file4)));

        final DefaultSearcher searcher = new DefaultSearcher(index);

        final Set<String> terms = new HashSet<>(Arrays.asList("abc", "def"));

        // when
        final Set<File> actual = searcher.search(terms);

        // then
        assertEquals(2, actual.size());
        assertTrue(actual.contains(file2));
        assertTrue(actual.contains(file4));
    }
}
