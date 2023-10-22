package org.example;

import java.util.ArrayList;

public class SearchHistory {
    private final static int limit = 20;
    public ArrayList<Integer> recentWord = new ArrayList<>();

    public void newSearch(int e) {
        while (recentWord.size() >= limit) {
            recentWord.remove(0);
        }
        recentWord.add(e);
    }

    public ArrayList<Integer> getRecentWord() {
        return recentWord;
    }

    public int getLimit() {
        return limit;
    }
}
