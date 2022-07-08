package edu.tec.ic6821.fulltextsearch.trie;

import java.util.Set;

public interface Trie<V> {
    void insert(String key, V value);
    Set<V> find(String key);
}
