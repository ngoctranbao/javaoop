package mycutedict.backend;

import java.util.ArrayList;

public class YourWord {
    protected ArrayList<Integer> yourPackage = new ArrayList<>();

    public ArrayList<Integer> getYourPackage() {
        return yourPackage;
    }

    protected void requireRemove(Integer e) {
        for (Integer integer:yourPackage) {
            if (integer.equals(e)) {
                yourPackage.remove(integer);
                return;
            }
        }
    }

    protected void requireAdd(Integer e) {
        if (yourPackage.size() >= 1000) {
            System.out.println("Sorry, you reach limit. Please remove some word and add again!");
        }
        else {
            yourPackage.add(e);
        }
    }
}
