import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
public class DictionaryCommanline extends Dictionary {
    public static void showALlWords(Dictionary dictionary) {
        System.out.println("No\t\t|\t\tEnglish\t\t\t|\t\t\tVietnamese");
        int i = 0;
        //ArrayList<Word> words = dictionary.getWords();
        for (Word it : words) {
            String eng = it.getWord_target();
            String vn = it.getWord_explain();
            i++;
            System.out.println(i + "\t|\t" + eng + "\t|\t" + vn);
        }
    }

    public static void dictionaryBasic(Dictionary dictionary) {
        DictionaryManagement.insertFromFile(dictionary);
        DictionaryCommanline.showALlWords(dictionary);
    }

//    public static void dictionarySearcher(Dictionary dictionary) {
//
//    }


}
