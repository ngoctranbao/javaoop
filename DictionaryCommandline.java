package org.example;

import java.util.Scanner;

public class DictionaryCommandline {
    public static void main(String[] args) {

        DictionaryManagement hehe = new DictionaryManagement();

        Scanner myReading = new Scanner(System.in);
        System.out.println("This is My Dictionary!\n" +
                "[0] Exit\n" +
//                "[1] Add\n" +
//                "[2] Remove\n" +
//                "[3] Update\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
//                "[7] Game\n" +
//                "[8] Import from file\n" +
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
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    hehe.requireDisplay();
                    break;
                case "5":
                    hehe.requireLookUp();
                    break;
                case "6":
                    hehe.requireSearch();
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                default:
                    System.out.println("hmm, i don't understand, can you try again ?");
                    break;
            }
            if(!checkExit) {
                System.out.println("Next action: ");
            }
        }
        myReading.close();
    }

}
