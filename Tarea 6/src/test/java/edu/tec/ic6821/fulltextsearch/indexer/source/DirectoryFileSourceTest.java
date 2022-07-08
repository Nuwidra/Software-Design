package edu.tec.ic6821.fulltextsearch.indexer.source;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryFileSourceTest {

    @Test
    public void testIterateDirectoryFileSource() throws URISyntaxException {
        // given
        final File directory = new File(DirectoryFileSourceTest.class.getResource("/filesourcetest").toURI());
        final DirectoryFileSource fileSource = new DirectoryFileSource(directory);

        // when
        final Set<String> result = new HashSet<>();
        while (fileSource.hasNext()) {
            result.add(fileSource.next().getName());
        }

        // then
        final Set<String> expected = new HashSet<>(Arrays.asList(
            "file1", "file2", "file3", "file4", "filesourcetest", "dira", "dirb", "dirc"
        ));
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }
}
