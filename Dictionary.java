import java.util.ArrayList;

public class Dictionary {
    protected static final ArrayList<Word> words = new ArrayList<>();

    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     * insert a word into words dictionary
     * @param word
     */
    public void insertWord(Word word) {
        words.add(word);
    }

    /**
     * remove a word from dictionary
     * @param word
     */
    public void removeWord(Word word) {
        words.remove(word);
    }

    /**
     * get all target words.
     * return array list contains the target words column
     */
    public ArrayList<String> getTargetWords() {
        ArrayList<String> targetWords = new ArrayList<String>();
        for (Word it : words) {
            targetWords.add(it.getWord_target());
        }
        return targetWords;
    }


}
