package com.anisaha.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anirban Saha (astle.theauthor@gmail.com)
 */
public class Trie {

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Iterative implementation of trie insert
     *
     * @param word to insert into the trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode child = current.children.get(c);
            if (child == null) {
                child = new TrieNode();
                current.children.put(c, child);
            }
            current = child;
        }
        // mark the current node's endofword to true
        current.endOfWord = true;
    }

    /**
     * Recursive implementation of the trie insert
     *
     * @param word to insert into the trie
     */
    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            current.endOfWord = true;
            return;
        }

        char c = word.charAt(index);
        TrieNode child = current.children.get(c);

        if (child == null) {
            child = new TrieNode();
            current.children.put(c, child);
        }
        insertRecursive(child, word, index + 1);
    }

    /**
     * Iterative implementation of search in trie
     *
     * @param word to search in the trie
     * @return true if word exist in the trie
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode child = current.children.get(c);
            if (child == null)
                return false;

            current = child;
        }
        // return true if endOfWord is true, else false
        return current.endOfWord;
    }

    /**
     * Recursive implementation of search in trie
     *
     * @param word to search in trie
     * @return true if word exist in trie
     */
    public boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode current, String word, int index) {
        if (index == word.length())
            return current.endOfWord;

        char c = word.charAt(index);
        TrieNode child = current.children.get(c);
        if (child == null)
            return false;

        return searchRecursive(child, word, index + 1);
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            // when end of word is reached only delete if current.endOfword is true;
            if (!current.endOfWord)
                return false;

            current.endOfWord = false;
            // if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char c = word.charAt(index);
        TrieNode child = current.children.get(c);
        if (child == null)
            return false;

        boolean shouldDeleteCurrent = delete(child, word, index + 1);

        // if delete was success, remove from map
        if (shouldDeleteCurrent) {
            current.children.remove(c);
            // if no mapping left in map
            return current.children.size() == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abcd");
        trie.insert("abgl");
        trie.insertRecursive("lmn");
        trie.insertRecursive("lmnpq");
        trie.insert("cdeg");
        trie.insert("ghijk");

        StringBuilder str = new StringBuilder();
        str.append(trie.search("ab") + "\n");
        str.append(trie.search("abcd") + "\n");

        trie.delete("abcd");
        str.append(trie.search("abgl") + "\n");
        str.append(trie.search("abcd") + "\n");

        trie.delete("lmn");
        str.append(trie.search("lmn") + "\n");
        str.append(trie.search("lmnpq") + "\n");

        trie.delete("lmnpq");
        str.append(trie.search("lmnpq") + "\n");

        System.out.println(str.toString());
    }

}