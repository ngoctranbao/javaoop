package mycutedict.backend;

import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    protected HashMap<Character,Node> roots = new HashMap<>();
    protected Database database = new Database();

    protected Dictionary() {
        insert();
    }

    public ArrayList<Word> getDatabase() {
        return database.dictionary;
    }

    /**
     * Args: looking for a part of a word.
     * Input: a part of english word.
     * Output: a list index of potential word in database.
     */
    public ArrayList<Integer> lookUp(String data) {
        return new ArrayList<>(lookUpFor(roots.get(data.charAt(0)), data.substring(1)));
    }

    private ArrayList<Integer> lookUpFor(Node node, String data) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (node == null) {
            return ans;
        }
        if (!data.isEmpty()) {
            return lookUpFor(node.children.get(data.charAt(0)), data.substring(1));
        }
        else {
            ans.addAll(disPlay(node));
        }
        return ans;
    }

    private ArrayList<Integer> disPlay(Node node) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (node == null) {
            return null;
        }
        if (node.endOfWord) {
            ans.add(node.wordIndex);
        }
        for (Character key: node.children.keySet()) {
            ans.addAll(disPlay(node.children.get(key)));
        }
        return ans;
    }

    /**
     * search for word.
     * Input: a english word
     * Output: postion of this word
     */
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
     * 
     * Insert all word to the hash.
     * Input: none.
     * Ouput: none.
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

    /**
     * add a word in hash
     * Input: word, index of word in database
     * Output none
     */
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