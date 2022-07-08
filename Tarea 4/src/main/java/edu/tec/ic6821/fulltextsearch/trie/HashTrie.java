package edu.tec.ic6821.fulltextsearch.trie;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

public final class HashTrie<V> implements Trie<V>, Serializable {

    public final class TrieNode implements Serializable {
        private final Map<Character, TrieNode> children;
        private final Set<V> bucket;
        public TrieNode() {
            this.children = new HashMap<>();
            this.bucket = new HashSet<>();
        }
    }
    
    private final TrieNode root;
    
    public HashTrie() {
        this.root = new TrieNode();
    }

    @Override
    public void insert(String key, V value) {
        TrieNode current = this.root;
        for (char c : key.toCharArray()) {
            final  TrieNode newCurrent = current.children.get(c);
            if (newCurrent == null) {
                final TrieNode node = new TrieNode();
                current.children.put(c, node);
                current = node;
            } else {
                current.children.put(c, newCurrent);
                current = newCurrent;
            }
        }
        current.bucket.add(value);
    }

    @Override
    public Set<V> find(String key) {
        TrieNode current = this.root;
        for (char c : key.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return Collections.emptySet();
            }
        }
        return current.bucket;
    }
}
