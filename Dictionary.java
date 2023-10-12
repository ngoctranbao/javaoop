package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Dictionary {
    private final String fileName;
    protected ArrayList<Word> dictionary = new ArrayList<>(1);
    public Dictionary(String fileName) {
        this.fileName = fileName;
    }

    /*import from file.*/
    protected void importFromFile() {
        try {
            File myCheck = new File(fileName);
            Scanner myReader = new Scanner(myCheck);
            String dataWordTarget;
            String dataWordExplain;
            while (myReader.hasNextLine()) {
                dataWordTarget = myReader.nextLine();
                if (myReader.hasNextLine()) {
                    dataWordExplain = myReader.nextLine();
                }
                else {
                    dataWordExplain = "";
                }
                Word temp = new Word(dataWordTarget,dataWordExplain);
                dictionary.add(temp);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("wait ...");
        }
    }

    /*Export to file.*/
    protected void exportToFile() {
        try {
            FileWriter myWriter = new FileWriter("dictionaries.txt");
            int cnt = 0;
            while (cnt < dictionary.size()) {
                myWriter.write(dictionary.get(cnt).getWord_target() + "\n");
                myWriter.write(dictionary.get(cnt).getWord_explain() + "\n");
                cnt++;
            }
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("ouch, we have a bug.");
        }
    }

    /*find the word from e_word*/
    protected int searchWord(String data) {
        int cnt = 0;
        while (cnt < dictionary.size()) {
            if (data.equals(dictionary.get(cnt).getWord_target())) {
                return cnt;
            }
            cnt++;
        }
        return -1;
    }

    /*print out word*/
    protected void printWord(int index) {
        if (index >= 0) {
            System.out.println(dictionary.get(index).getWord_target() + ": " + dictionary.get(index).getWord_explain());
        } else {
            System.out.println("can not find word");
        }
    }



}