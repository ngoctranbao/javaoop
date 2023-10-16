package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    private final Scanner temporary = new Scanner(System.in);
    private final SearchHistory searchHistory = new SearchHistory();
    private final YourWord yourWord = new YourWord();

    public DictionaryManagement() {
        fileManage.importFromFile("src/main/resources/recentsearchword.txt", searchHistory.recentWord);
        fileManage.importFromFile("src/main/resources/wordpackage.txt", yourWord.yourPackage);
    }

    public void requireExit() {
        fileManage.exportToFile("src/main/resources/recentsearchword.txt", searchHistory.recentWord);
        fileManage.exportToFile("src/main/resources/wordpackage.txt", yourWord.yourPackage);
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

    public void requireAdd() {
        String data = temporary.nextLine();
        int cnt = search(data);
        if (cnt < 0) {
            System.out.println("You can't add this word");
        }
        else {
            yourWord.requireAdd(fileManage.dictionary.get(cnt));
            System.out.println("Add success");
        }
    }

    public void requireRemove() {
        String data = temporary.nextLine();
        int cnt = search(data);
        if (cnt < 0) {
            System.out.println("We don't even have this word");
        }
        else {
            yourWord.requireRemove(fileManage.dictionary.get(cnt));
        }
    }

    public void requireShowUpList() {
        yourWord.requireDisplay();
    }
}
