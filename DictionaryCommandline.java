package org.example;

import java.util.Scanner;

public class DictionaryCommandline {
    public static void main(String[] args) {

        DictionaryManagement hehe = new DictionaryManagement();

        Scanner myReading = new Scanner(System.in);
        System.out.println("This is My Dictionary!\n" +
                "[0] Exit\n" +
                "[1] Add to your list\n" +
                "[2] Remove from your list\n" +
                "[3] Update actually mean show up your list\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
                "[7] Game\n" +
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
                    hehe.requireRemove();
                    break;
                case "3":
                    hehe.requireShowUpList();
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
                    hehe.requireGame();
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
