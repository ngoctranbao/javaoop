package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManage {
    protected String fileName = "src\\main\\resources\\dictionaries.txt";
    protected ArrayList<Word> dictionary = new ArrayList<>();

    public FileManage() {
        importFromFile(fileName,dictionary);
    }

    public void importFromFile(String name, ArrayList<Word> dic) {
        try {
            File myCheck = new File(name);
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
                dic.add(temp);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("wait ...");
        }
    }

    protected void exportToFile(String name,ArrayList<Word> dic) {
        try {
            FileWriter myWriter = new FileWriter(name);
            int cnt = 0;
            while (cnt < dic.size()) {
                myWriter.write(dic.get(cnt).getWord_target() + "\n");
                myWriter.write(dic.get(cnt).getWord_explain() + "\n");
                cnt++;
            }
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("ouch, we have a bug.");
        }
    }
}
