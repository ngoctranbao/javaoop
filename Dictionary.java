package org.example;

import java.util.ArrayList;
import java.util.HashMap;


public class Dictionary {
    protected HashMap<Character,Node> roots = new HashMap<>();
    protected Database database = new Database();

    public Dictionary() {
        insert();
    }

    public ArrayList<Word> lookUp(String data) {
        return new ArrayList<>(lookUpFor(roots.get(data.charAt(0)), data.substring(1)));
    }

    private ArrayList<Word> lookUpFor(Node node, String data) {
        ArrayList<Word> ans = new ArrayList<>();
        if (node == null) {
            return ans;
        }
        if (!data.isEmpty()) {
            return lookUpFor(node.children.get(data.charAt(0)), data.substring(1));
        }
        else {
            if (node.endOfWord) {
                ans.add(database.dictionary.get(node.wordIndex));
            }
            ans.addAll(disPlay(node));
        }
        return ans;
    }

    private ArrayList<Word> disPlay(Node node) {
        ArrayList<Word> ans = new ArrayList<>();
        if (node == null) {
            return null;
        }
        if (node.endOfWord) {
            ans.add(database.dictionary.get(node.wordIndex));
        }
        for (Character key: node.children.keySet()) {
            ans.addAll(disPlay(node.children.get(key)));
        }
        return ans;
    }

    public int search(String data) {
        if (roots.containsKey(data.charAt(0))) {
            return searchFor(roots.get(data.charAt(0)),data.substring(1));
        }
        return -1;
    }

    private int searchFor(Node node, String data) {
        if (node == null) {
            return -1;
        }
        if (data.isEmpty()) {
            if (node.endOfWord) {
                return node.wordIndex;
            }
            else {
                return -1;
            }
        }
        return searchFor(node.children.get(data.charAt(0)), data.substring(1));
    }


    /**
     * Insert a word into the dictionary.
     * The word to insert.
     */
    private void insert() {
        for (int i = 0; i < database.dictionary.size(); i++) {
            String string = database.dictionary.get(i).getWord_target();
            if (!roots.containsKey(string.charAt(0))) {
                roots.put(string.charAt(0), new Node());
            }

            insertWord(string.substring(1),i, roots.get(string.charAt(0)));
        }
    }

    //Adds a new word to the trie tree.
    private void insertWord(String string,int wordIndex, Node node) {
        final Node nextChild;
        try {
            if (string.isEmpty()){
                return;
            }

            if (node.children.containsKey(string.charAt(0))) {
                nextChild = node.children.get(string.charAt(0));
            } else {
                nextChild = new Node();
                node.children.put(string.charAt(0), nextChild);
            }

            if (string.length() == 1) {
                nextChild.endOfWord = true;
                nextChild.wordIndex = wordIndex;
            } else {
                insertWord(string.substring(1),wordIndex,nextChild);
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("insert wrong and i don't know why");
        }
    }
}