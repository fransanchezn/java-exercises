package org.fransanchez.deprecated.tree;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(final String word) {
        var current = root;
        for (char l: word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setWord(true);
    }

    public boolean find(final String word) {
        var current = root;
        for(var l: word.toCharArray()) {
            var node = current.getChildren().get(l);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isWord();
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("hola");

        System.out.println(t.find("hola"));
    }
}
