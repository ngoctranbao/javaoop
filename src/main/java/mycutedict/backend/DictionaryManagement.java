package mycutedict.backend;

import java.sql.SQLException;
import java.util.ArrayList;

public class DictionaryManagement extends Dictionary {

    private final SearchHistory searchHistory = new SearchHistory();
    private final YourWord yourWord = new YourWord();

    public DictionaryManagement() {
        database.importFromFile("src/main/resources/mycutedict/DataFile/recentsearchword.txt", searchHistory.recentWord);
        database.importFromFile("src/main/resources/mycutedict/DataFile/wordpackage.txt", yourWord.yourPackage);
    }

    public void requireExit() {
        database.exportToFile("src/main/resources/mycutedict/DataFile/recentsearchword.txt", searchHistory.recentWord);
        database.exportToFile("src/main/resources/mycutedict/DataFile/wordpackage.txt", yourWord.yourPackage);
        database.close();
    }

    public void requireLookUp(String data) {
        ArrayList<Word> temp;
        temp = this.lookUp(data);
        for (Word word : temp) {
            System.out.println(word);
        }
    }

    public Word requireSearch(String data) {
        int cnt = search(data);
        if (cnt < 0) {
            return null;
        }
        searchHistory.newSearch(cnt);
        return database.dictionary.get(cnt);
    }
    public Word requireSearch(Integer cnt) {
        if (cnt < 0) {
            return  null;
        }
        return database.dictionary.get(cnt);
    }

    public ArrayList<Integer> requireShowUpRecentWord() {
        return searchHistory.getRecentWord();
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
