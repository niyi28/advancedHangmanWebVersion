package com.company.advancedHangman.BankEndDevelopment.Hangman;

import java.util.HashMap;

public class HangmanDoom {

    public HangmanDoom (){
    }

    private HashMap<Integer, String> hangmanDrawingWhenPlayerFails (){
        /*"#"*/
        HashMap<Integer, String> hangmanDraw = new HashMap<Integer, String>();
        hangmanDraw.put(8, "  _______\n  |\n  |\n  |\n  |\n  |\n _|________");
        hangmanDraw.put(7, "  _______\n  |/   | \n  |    O \n  |\n  |\n  |\n _|________");
        hangmanDraw.put(6, "  _______\n  |/   | \n  |    O \n  |    |\n  |    \n  |\n _|________");
        hangmanDraw.put(5, "  _______\n  |/   | \n  |    O \n  |    |\n  |    |\n  |\n _|________");
        hangmanDraw.put(4, "  _______\n  |/   | \n  |    O \n  |   \\|\n  |    | \n _|________");
        hangmanDraw.put(3, "  _______\n  |/   | \n  |    O \n  |   \\|/\n  |    | \n  |    \n\n _|________");
        hangmanDraw.put(2, "  _______\n  |/   | \n  |    O \n  |   \\|/\n  |    | \n  |   /\n\n _|________");
        hangmanDraw.put(1, "  _______\n  |/   | \n  |    O \n  |   \\|/\n  |    | \n  |   / \\\n _|________" + "\n***********\n*         *\n*Game Over*\n*         *\n***********");

        return hangmanDraw;
    }

    private String getHangmanDrawingWhenPlayerFails (int numberOfFailedTries){
        HashMap<Integer, String> hangmanDraw = hangmanDrawingWhenPlayerFails();
        String value = "";

        for (HashMap.Entry<Integer, String> map : hangmanDraw.entrySet()) {
            if(numberOfFailedTries == map.getKey()){
                value = map.getValue();
            }
        }
        return value;
    }

    public void printhangmanDrawingWhenPlayerFails (int numberOfFailedTries){
        int numberOfTriesLeft = numberOfFailedTries - 1;
        System.out.println(getHangmanDrawingWhenPlayerFails(numberOfFailedTries) + "   " + numberOfTriesLeft + " incorrect try(ies) left.\n");
    }

}






