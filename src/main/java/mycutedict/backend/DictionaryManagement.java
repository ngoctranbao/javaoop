package mycutedict.backend;

import java.sql.SQLException;
import java.util.ArrayList;

public class DictionaryManagement extends Dictionary {

    private final SearchHistory searchHistory = new SearchHistory();
    private final YourWord yourWord = new YourWord();
    private final Game game = new Game(yourWord.getYourPackage());

    public DictionaryManagement() {
        database.importFromFile("src/main/resources/mycutedict/DataFile/recentsearchword.txt", searchHistory.recentWord);
        database.importFromFile("src/main/resources/mycutedict/DataFile/wordpackage.txt", yourWord.yourPackage);
    }

    public void requireExit() {
        database.exportToFile("src/main/resources/mycutedict/DataFile/recentsearchword.txt", searchHistory.recentWord);
        database.exportToFile("src/main/resources/mycutedict/DataFile/wordpackage.txt", yourWord.yourPackage);
        database.close();
    }

    public ArrayList<Integer> requireLookUp(String data) {
        ArrayList<Integer> temp;
        temp = this.lookUp(data);
        return temp;
    }

    /** find out the index of word in YourWord
     * Input: word target
     * Return: index, if not return -1.*/
    public int isSaved(String word_target) {
        int temp = search(word_target);
        return yourWord.indexOfYourWord(temp);
    }

    /** find out the index of word in YourWord
     * Input: word target
     * Return: index, if not return -1.*/
    public int isSaved(String word_target) {
        int temp = search(word_target);
        return yourWord.indexOfYourWord(temp);
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
        searchHistory.newSearch(cnt);
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

    public ArrayList<Integer> requireShowUpYourWord() {
        return yourWord.yourPackage;
    }

    public Game requireGame() {
        return game;
    }
}
