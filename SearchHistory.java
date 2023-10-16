package org.example;

import java.util.ArrayList;

public class SearchHistory {
    private final static int limit = 20;
    public ArrayList<Word> recentWord = new ArrayList<>();

    public SearchHistory() {}

    public void newSearch(Word e) {
        recentWord.add(e);
    }

    public void requireDisplay() {
        if (recentWord.isEmpty()) {
            System.out.println("You have not search anything");
            return;
        }
        while (recentWord.size() >= limit) {
            recentWord.remove(0);
        }
        int cnt = recentWord.size() - 1;
        while (cnt >= 0) {
            System.out.println(recentWord.get(cnt).getWord_target() + ": " + recentWord.get(cnt).getWord_explain());
            cnt--;
        }
    }
}
