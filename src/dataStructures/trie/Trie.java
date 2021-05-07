package dataStructures.trie;

class Trie {

    static class Node {

        Node[] child;
        boolean end;

        Node() {
            child = new Node[26];
            end = false;
        }

    }

    Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            if(cur.child[c-97] == null) cur.child[c-97] = new Node();
            cur = cur.child[c-97];
        }
        cur.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            if(cur.child[c-97] == null) return false;
            cur = cur.child[c-97];
        }
        return cur.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i++) {
            char c = prefix.charAt(i);
            if(cur.child[c-97] == null) return false;
            cur = cur.child[c-97];
        }
        return true;
    }
}
