package org.example;

import java.util.ArrayList;

public class DictionaryManagement extends Dictionary {

    private final SearchHistory searchHistory = new SearchHistory();
    private final YourWord yourWord = new YourWord();
    private final Game game = new Game();

    public DictionaryManagement() {
        database.importFromFile("src/main/resources/recentsearchword.txt", searchHistory.recentWord);
        database.importFromFile("src/main/resources/wordpackage.txt", yourWord.yourPackage);
    }

    public void requireExit() {
        database.exportToFile("src/main/resources/recentsearchword.txt", searchHistory.recentWord);
        database.exportToFile("src/main/resources/wordpackage.txt", yourWord.yourPackage);
        database.close();
    }

    public void requireLookUp(String data) {
        ArrayList<Word> temp;
        temp = this.lookUp(data);
        for (Word word : temp) {
            System.out.println(word);
        }
    }

    public void requireSearch(String data) {
        int cnt = search(data);
        if (cnt < 0) {
            System.out.println("Sorry, we may don't have this word");
        }
        else {
            searchHistory.newSearch(cnt);
            System.out.println(database.dictionary.get(cnt));
        }
    }

    public void requireShowUpRecentWord() {
        if (searchHistory.recentWord.isEmpty()) {
            System.out.println("You have not search anything");
            return;
        }
        int cnt = searchHistory.recentWord.size() - 1;
        while (cnt >= 0) {
            int temp = searchHistory.recentWord.get(cnt);
            System.out.println(database.dictionary.get(temp));
            cnt--;
        }
    }

    public void requireAdd(String data) {
        int cnt = search(data);
        if (cnt < 0) {
            System.out.println("You can't add this word");
        }
        else {
            yourWord.requireAdd(cnt);
            System.out.println("Add success");
        }
    }

    public void requireGame() {
        game.game();
    }

    public void requireRemove(String data) {
        int cnt = search(data);
        if (cnt < 0) {
            System.out.println("We don't even have this word");
        }
        else {
            yourWord.requireRemove(cnt);
        }
    }

    public void requireShowUpYourWord() {
        int cnt = 0;
        while (cnt < yourWord.yourPackage.size()) {
            int temp = yourWord.yourPackage.get(cnt);
            System.out.println(database.dictionary.get(temp));
            cnt++;
        }
    }
}
