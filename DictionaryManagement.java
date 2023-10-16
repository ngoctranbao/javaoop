package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    private final Scanner temporary = new Scanner(System.in);
    private final SearchHistory searchHistory = new SearchHistory();

    public DictionaryManagement() {
        fileManage.importFromFile("src/main/resources/recentsearchword.txt", searchHistory.recentWord);
    }

    public void requireExit() {
        fileManage.exportToFile("src/main/resources/recentsearchword.txt", searchHistory.recentWord);
        temporary.close();
    }

    public void requireLookUp() {
        String data = temporary.nextLine();
        ArrayList<Word> temp;
        temp = this.lookUp(data);
        for (Word word : temp) {
            System.out.println(word.getWord_target() + ": " + word.getWord_explain());
        }
    }

    public void requireSearch() {
        String data = temporary.nextLine();
        int cnt = search(data);
        if (cnt < 0) {
            System.out.println("Sorry, we may don't have this word");
        }
        else {
            searchHistory.newSearch(fileManage.dictionary.get(cnt));
            System.out.println(fileManage.dictionary.get(cnt).getWord_target() + ": " + fileManage.dictionary.get(cnt).getWord_explain());
        }
    }

    public void requireDisplay() {
        searchHistory.requireDisplay();
    }
}
