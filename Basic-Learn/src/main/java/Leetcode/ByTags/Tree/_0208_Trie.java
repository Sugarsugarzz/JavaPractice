package Leetcode.ByTags.Tree;


/**
 * 208. Trie前缀树
 * Medium
 */

public class _0208_Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

class Trie {

    class TrieNode {
        boolean isEnd;
        TrieNode[] next;

        TrieNode() {
            this.isEnd = false;
            this.next = new TrieNode[26];
        }
    }

    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.next[word.charAt(i) - 'a'] == null) {
                return false;
            }
            node = node.next[word.charAt(i) - 'a'];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                return false;
            }
            node = node.next[c - 'a'];
        }
        return true;
    }
}
