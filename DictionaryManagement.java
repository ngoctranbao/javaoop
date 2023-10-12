package org.example;

import java.util.Scanner;

public class DictionaryManagement extends Dictionary{
    private Scanner temporate = new Scanner(System.in);
    public DictionaryManagement(String fileName) {
        super(fileName);
        super.importFromFile();
    }

    public void requireSearch(String data) {
        printWord(searchWord(data));
    }

    public void requireAdd() {
        String wordTarget = temporate.nextLine();
        String wordExplain = temporate.nextLine();
        Word temp = new Word (wordTarget,wordExplain);
        String checkExist = temp.getWord_target();
        if(searchWord(checkExist) < 0) {
            dictionary.add(temp);
        }
    }

    public void requireRemove(String data) {
        if(searchWord(data) > 0) {
            dictionary.remove(searchWord(data));
        }
    }

    public void requireUpdate() {
        String wordTarget = temporate.nextLine();
        String wordExplain = temporate.nextLine();
        int index = this.searchWord(wordTarget);
        if (index < 0) {
            System.out.println("Don't have this word, want to add?");
        }
        else {
            dictionary.remove(index);
        }
        dictionary.add(new Word(wordTarget,wordExplain));
    }

    public void requireDisplay() {
        String limited = temporate.next();
        int limit = Integer.parseInt(limited);
        for (int i = 0; i<limit; i++) {
            printWord(i);
        }
    }

    public void requireExit() {
        super.exportToFile();
        temporate.close();
    }
}
