package edu.tec.ic6821.fulltextsearch.trie;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class HashTrie<V> implements Trie<V>, Serializable {

    private class TrieNode implements Serializable {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private final Set<V> bucket = new HashSet<>();
    }

    private final TrieNode root = new TrieNode();

    @Override
    public void insert(String key, V value) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            current = current.children.computeIfAbsent(c, __ -> new TrieNode());
        }

        current.bucket.add(value);
    }

    @Override
    public Set<V> find(String key) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return Collections.emptySet();
            }
        }

        return current.bucket;
    }
}
