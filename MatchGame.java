package database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class MatchGame {
    private ArrayList<Word> wordsInGame;
    private ArrayList<String> target = new ArrayList<String>();
    private ArrayList<String> explain = new ArrayList<String>();

    private ArrayList<String> shuffle_target = new ArrayList<String>();
    private int score;
    private int attempts;
    private static final int MAX_PAIRS = 6;

    /**
     * random wordsInGame list from bigList
     * bigList now is list of all words in dictionary
     * sau sua thanh lay tu yourWord sau
     * @param bigList
     */
    public MatchGame(ArrayList<Word> bigList) {
        wordsInGame = new ArrayList<>();
        score = 0;
        attempts = 5;
        Random rand = new Random();
        while (wordsInGame.size() < MAX_PAIRS) { // Get 6 random elements
            Random random = new Random();
            int index = random.nextInt(30000);
            Word element = bigList.get(index);
            if (!wordsInGame.contains(element)) { // Avoid duplicates
                wordsInGame.add(element);
            }
        }
        for (int i = 0; i < MAX_PAIRS; i++) {
            target.add(wordsInGame.get(i).getWord_target());
            explain.add(wordsInGame.get(i).getWord_explain());
            shuffle_target.add(wordsInGame.get(i).getWord_target());
        }
    }

    /**
     * shuffle target array cho nó không tương ứng với nhau để nối trong bản commandline
     * nối bằng cách nhập 2 từ
     */
    public void shuffle () {
        Collections.shuffle(shuffle_target);
    }

    /**
     * hiển thị giao diện đầu game bản commandline
     */
    public void display() {
        System.out.println("Matching game------------");
        System.out.println("Words\t\t\tDefinitions");
        shuffle();
        for (int i = 0; i < MAX_PAIRS; i++) {
            System.out.println(shuffle_target.get(i) + "\t\t\t" + explain.get(i));
        }
    }

    /**
     * check xem từ tiếng anh có đúng nghĩa tiếng việt như mình vừa nhập ko
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkAnswer(String s1, String s2) {
//        Scanner scanner = new Scanner(System.in);
//        s1 = scanner.nextLine();
//        scanner.nextLine();
//        s2 = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < MAX_PAIRS; i++) {
            if (target.get(i).equals(s1)) {
                index = i;
                break;
            }
        }
        return wordsInGame.get(index).getWord_explain().equals(s2);
    }

    /**
     * cộng điểm
     * @param result
     */
    public void updateScore(boolean result) {
        if (result) {
            score++;
        } else {
            attempts--;
        }
    }

    /**
     * nếu hết lượt đoán sai hoặc đã nối hết thì hết game
     * @return
     */
    public boolean isOver() {
        if (attempts == 0 || score == MAX_PAIRS) {
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }


}
