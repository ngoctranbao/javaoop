package mycutedict.backend;

import java.util.HashMap;

public class Node {
    public Node parent;
    public int wordIndex;
    public Boolean endOfWord; //Does this Node mark the end of a particular word?
    public HashMap<Character,Node> children = new HashMap<>();

    public Node() {
        this.endOfWord = false;
        this.wordIndex = -1;
    }
}