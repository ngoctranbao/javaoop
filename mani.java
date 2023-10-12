package org.example;

import java.util.Scanner;

public class mani {
    public static void main(String[] args) {

        DictionaryManagement hehe = new DictionaryManagement("dictionaries.txt");

        Scanner myReading = new Scanner(System.in);
        System.out.println("Welcome to My Application!\n" +
                "[0] Exit\n" +
                "[1] Add\n" +
                "[2] Remove\n" +
                "[3] Update\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
                "[7] Game\n" +
                "[8] Import from file\n" +
                "[9] Export to file\n"+
                "Your action: ");
        String data;

        boolean checkExit = false;
        while (!checkExit) {
            data = myReading.nextLine();
            switch (data) {
                case "0":
                    hehe.requireExit();
                    checkExit = true;
                    break;
                case "1":
                    hehe.requireAdd();
                    break;
                case "2":
                    data = myReading.nextLine();
                    hehe.requireRemove(data);
                    break;
                case "3":
                    hehe.requireUpdate();
                    break;
                case "4":
                    hehe.requireDisplay();
                    break;
//                case "5":
                case "6":
                    data = myReading.nextLine();
                    hehe.requireSearch(data);
                    break;
//                case "7":
                case "8":
                    hehe.importFromFile();
                    break;
                case "9":
                    hehe.exportToFile();
                    break;
                default:
                    System.out.println("hey, mistake");
                    break;
            }
            if(!checkExit) {
                System.out.println("Next action: ");
            }
        }
        myReading.close();
    }
}
