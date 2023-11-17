package mycutedict.backend;

import java.util.ArrayList;

public class YourWord {
    protected ArrayList<Integer> yourPackage = new ArrayList<>();

    public ArrayList<Integer> getYourPackage() {
        return yourPackage;
    }

    /**
     * remove word from your word
     * @param e
     * Input: index of this word
     * Output: 
     */
    protected void requireRemove(Integer e) {
        for (Integer integer:yourPackage) {
            if (integer.equals(e)) {
                yourPackage.remove(integer);
                return;
            }
        }
    }

    /**
     * add word from your word
     * @param e
     * Input: index of this word
     * Output: 
     */
    protected void requireAdd(Integer e) {
        if (yourPackage.size() >= 1000) {
            System.out.println("Sorry, you reach limit. Please remove some word and add again!");
        }
        else {
            yourPackage.add(e);
        }
    }

    public int indexOfYourWord(int wordTargetIndex) {
        for (int i = 0; i< yourPackage.size(); i++) {
            if (yourPackage.get(i) == wordTargetIndex) {
                return i;
            }
        }
        return -1;
    }
}
