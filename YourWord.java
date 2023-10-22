package org.example;

import java.util.ArrayList;

public class YourWord {
    protected ArrayList<Word> yourPackage = new ArrayList<>();

    protected void requireRemove(Word e) {
        for (Word word:yourPackage) {
            if (word.equals(e)) {
                yourPackage.remove(word);
                return;
            }
        }
    }

    protected void requireDisplay() {
        int cnt = 0;
        while (cnt < yourPackage.size()) {
            System.out.println(yourPackage.get(cnt).getWord_target() + ": " + yourPackage.get(cnt).getWord_explain());
            cnt++;
        }
    }

    protected void requireAdd(Word e) {
        if (yourPackage.size() >= 1000) {
            System.out.println("Sorry, you reach limit. Please remove some word and add again!");
        }
        else {
            yourPackage.add(e);
        }
    }
}
