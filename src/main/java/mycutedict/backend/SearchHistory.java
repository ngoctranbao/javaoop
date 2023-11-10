package mycutedict.backend;

import java.util.ArrayList;
import java.util.Collections;

public class SearchHistory {
    private final static int limit = 50;
    public ArrayList<Integer> recentWord = new ArrayList<>();

    public void newSearch(int e) {
        while (recentWord.size() >= limit) {
            recentWord.remove(recentWord.size() - 1);
        }
        recentWord.add(0,e);
    }

    public ArrayList<Integer> getRecentWord() {
        if(recentWord.isEmpty()) {
            return null;
        }
        return recentWord;
    }

    public int getLimit() {
        return limit;
    }
}
