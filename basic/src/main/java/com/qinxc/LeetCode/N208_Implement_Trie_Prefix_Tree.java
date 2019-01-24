package com.qinxc.LeetCode;

/**
 * @author qxc
 * @date 2019/1/22.
 */
public class N208_Implement_Trie_Prefix_Tree {

    public static void main(String[] args) {
        String word = "apple";
        String prefix = "app";
        Trie obj = new Trie();
        obj.insert(word);
        boolean param_2 = obj.search(word);
        boolean param_3 = obj.startsWith(prefix);
        System.out.println(param_2);
        System.out.println(param_3);
    }

}

class TireNode {
    public TireNode[] child = new TireNode[26];
    public boolean isWord = false;

}

class Trie {

    public TireNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        this.root = new TireNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            if (node.child[temp - 'a'] == null) {
                node.child[temp - 'a'] = new TireNode();
            }
            node = node.child[temp - 'a'];
        }
        node.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            if (node.child[temp - 'a'] != null) {
                node = node.child[temp - 'a'];
            } else {
                return false;
            }
        }
        if (node.isWord)
            return true;
        else
            return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TireNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char temp = prefix.charAt(i);
            if (node.child[temp - 'a'] != null) {
                node = node.child[temp - 'a'];
            } else {
                return false;
            }
        }
        return true;
    }
}
