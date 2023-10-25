package database;

import java.util.ArrayList;
import java.util.Scanner;

public class MatchGameUi {
    private MatchGame game;
    //private ArrayList<Word> words = new ArrayList<>();

    public MatchGameUi(ArrayList<Word> words) {
        game = new MatchGame(words);
    }

    public void showMenu() {
        System.out.println("Welcome to the match words with their definition game!");
        System.out.println("The goal is to match each word with its correct definition.");
        System.out.println("You can enter two indices of the word and definition you want to match.");
        System.out.println("If they match, they will be shown. If they don't match, you will lose an attempt.");
        System.out.println("The game ends when you match all the words or run out of attempts.");
        System.out.println("Good luck!");
        System.out.println();
    }

    /**
     * Nhập từ tiếng anh và từ tiếng việt để nối
     * @return
     */
    public String[] getInput() {
        Scanner scanner = new Scanner(System.in);
        String[] input = new String[2];
        System.out.println("Enter the word target: ");
        input[0] = scanner.nextLine();
        System.out.println("Enter the definition: ");
        input[1] = scanner.nextLine();
        return input;
    }

    public void play() {
        showMenu();
        while(!game.isOver()) {
            game.display();
            String[] input = getInput();
            boolean result = game.checkAnswer(input[0], input[1]);
            game.updateScore(result);
            if (result) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
            System.out.println();
        }
        game.display();
        System.out.println("Your score: " + game.getScore());
    }

}
