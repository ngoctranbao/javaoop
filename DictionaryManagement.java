import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    /**
     * insert words data from keyboard
     */
    public static void insertFromCommandLine(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of words: ");
        int word_number = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < word_number; i++) {
            System.out.println("Enter the English word: ");
            String eng_word = scanner.nextLine();
            System.out.println("Enter the Vietnamese word: ");
            String vn_word = scanner.nextLine();
            Word new_word = new Word(eng_word, vn_word);
            dictionary.insertWord(new_word);
        }
    }

    public static void insertFromFile(Dictionary dictionary) {
        String filePath = new File("").getAbsolutePath() + "\\src\\main\\resources\\dictionaries.txt";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String word_target = bufferedReader.readLine();
            String word_explain = bufferedReader.readLine();
            while (word_target != null && word_explain != null) {
                Word new_word = new Word(word_target, word_explain);
                dictionary.insertWord(new_word);
                word_target = bufferedReader.readLine();
                word_explain = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException e) {
            System.out.println("I/O exception " + e);
        }
    }

    public static String dictionaryLookup() {
        //ArrayList<Word> words = dictionary.getWords();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word you need to look up: ");
        String lookup_word = scanner.nextLine();
        for (Word it : words) {
            if (it.getWord_target().equals(lookup_word)) {
                return it.getWord_explain();
            }
        }
        return "Cannot find the word being looked up";
    }

    public static void dictionaryEdit() {
        //ArrayList<Word> words = dictionary.getWords();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word need editing: ");
        String need_edit_word = scanner.nextLine();
        boolean flag = false;
        for (Word it : words) {
            if (it.getWord_target().equals(need_edit_word)) {
                System.out.println("The meaning you want to change the word to: ");
                String after_edit_word = scanner.nextLine();
                it.setWord_explain(after_edit_word);
                System.out.println("Updated!");
                flag = true;
                return;
            }
        }
        if (!flag) {
            System.out.println("Dictionary does not contain this word");
        }
    }

    public static void dictionaryRemove(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the to be removed word: ");
        String tobe_removed_word = scanner.nextLine();
        boolean flag = false;
        for (Word it : words) {
            if (it.getWord_target().equals(tobe_removed_word)) {
                dictionary.removeWord(it);
                flag = true;
                return;
            }
        }
        if (!flag) {
            System.out.println("Dictionary does not contain this word");
        }
    }


}
