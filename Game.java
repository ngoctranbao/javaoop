package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final static Database database = new Database();
    private final ArrayList<Integer> dictionary = new ArrayList<>();

    public Game() {
        database.importFromFile("src/main/resources/wordpackage.txt", dictionary);
    }
    private final Random rand = new Random(System.currentTimeMillis());

    private String wordChange(String a) {
        StringBuilder ans = new StringBuilder();
        int n = a.length() - 1;
        while (n >= 0) {
            ans.append("_");
            n--;
        }
        return ans.toString();
    }

    private void question(Word e,Integer wrongAnswer) {
        System.out.println(e.getWord_explain());
        System.out.println(wordChange(e.getWord_target()));
        System.out.println("Your answer");
        Scanner myReading = new Scanner(System.in);
        String data;
        while (wrongAnswer < 5) {
            data = myReading.nextLine();
            if (data.equals(e.getWord_target())) {
                System.out.println("correct");
                break;
            } else {
                System.out.println("wrong, try again!");
                wrongAnswer++;
            }
        }
    }

    public void game() {
        int [] temp = new int[10];
        for (int i = 0; i < 10;i++) {
            temp[i] = Math.abs(rand.nextInt(dictionary.size()));
        }
        Integer wrongAnswer = 0;

        for (int i = 0;i < 10; i++) {
            question(database.dictionary.get(dictionary.get(temp[i])), wrongAnswer);
            if (wrongAnswer >= 5) {
                System.out.println("You lose!");
                return;
            }
        }
        System.out.println("Congratulation! We have winner");
    }
}
