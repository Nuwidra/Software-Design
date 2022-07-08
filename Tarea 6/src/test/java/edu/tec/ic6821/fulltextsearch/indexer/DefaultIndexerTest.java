package edu.tec.ic6821.fulltextsearch.indexer;

import edu.tec.ic6821.fulltextsearch.index.PersistentIndex;
import edu.tec.ic6821.fulltextsearch.indexer.source.FileSource;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.FileTokenizer;
import edu.tec.ic6821.fulltextsearch.indexer.tokenizer.FileTokenizerFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class DefaultIndexerTest {

    @Test
    public void testIndex() {
        // given
        final File file1 = new File(".file1");
        final File file2 = new File(".file2");

        final FileSource fileSource = Mockito.mock(FileSource.class);
        when(fileSource.hasNext())
            .thenReturn(true)
            .thenReturn(true)
            .thenReturn(false);

        when(fileSource.next())
            .thenReturn(file1)
            .thenReturn(file2)
            .thenReturn(null);

        final FileTokenizer fileTokenizer = Mockito.mock(FileTokenizer.class);
        when(fileTokenizer.hasNext())
            .thenReturn(true)
            .thenReturn(true)
            .thenReturn(false);

        when(fileTokenizer.next())
            .thenReturn("abc")
            .thenReturn("def")
            .thenReturn(null);

        final FileTokenizerFactory fileTokenizerFactory = Mockito.mock(FileTokenizerFactory.class);
        when(fileTokenizerFactory.fileTokenizer(any(File.class)))
            .thenReturn(Optional.of(fileTokenizer))
            .thenReturn(Optional.empty());

        final PersistentIndex index = Mockito.mock(PersistentIndex.class);
        doNothing().when(index).index("abc", file1);
        doNothing().when(index).index("def", file1);


        final Indexer indexer = new DefaultIndexer(fileSource, fileTokenizerFactory, index);

        // when
        final int[] callCount = {0};
        indexer.index(file -> callCount[0]++);

        // then
        assertEquals(2, callCount[0]);
    }
}
