package edu.tec.ic6821.fulltextsearch.index;


import edu.tec.ic6821.fulltextsearch.trie.Trie;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Set;

public final class TrieIndex implements PersistentIndex {
    private final File indexFile;
    private Trie<File> index;
    public TrieIndex(final File location, final Trie<File> trie) {
        this.indexFile = location;
        this.index = trie;
    }

    @Override
    public void index(String term, File file) {
        index.insert(term, file);
    }

    @Override
    public Set<File> search(String term) {
        return index.find(term);
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public void persist() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.indexFile))) {
            out.writeObject(this.index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean restore() {
        if (!this.indexFile.exists()) {
            return false;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.indexFile))) {
            this.index = (Trie<File>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
