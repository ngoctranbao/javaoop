package mycutedict.backend;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final ArrayList<Integer> yourWordList;

    /* Store 10 random words from yourWordList at the start of every game */
    public static final ArrayList<Integer> tenWords = new ArrayList<>();
    /* Store 4 random words (one is the key to the question) for every question  */
    public static final ArrayList<Integer> fourWordsEachQuestion = new ArrayList<>();

    private final Random rand = new Random(System.currentTimeMillis());

    public Game(ArrayList<Integer> yourWordList) {
        this.yourWordList = yourWordList;
    }

    /**
     * Random a list of 10 integer in your words list
     * Loop through each (each random three more integers from your words list)
     */

    public void random10Words() {
        tenWords.clear(); // Clear the list before populating with new values

        for (int i = 0; i < 10; ++i) {
            int randomIndex = rand.nextInt(yourWordList.size());
            tenWords.add(yourWordList.get(randomIndex));
        }
    }


    public void random4Answers(Integer integer) {
        fourWordsEachQuestion.clear(); // Clear the list before populating with new values

        fourWordsEachQuestion.add(integer); // Set the key at index 0

        for (int i = 1; i < 4; ++i) {
            int randomIndex = rand.nextInt(yourWordList.size());
            fourWordsEachQuestion.add(yourWordList.get(randomIndex));
        }

        Collections.shuffle(fourWordsEachQuestion);
    }

}

