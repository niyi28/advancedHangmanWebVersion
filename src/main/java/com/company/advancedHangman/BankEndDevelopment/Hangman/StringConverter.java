package com.company.advancedHangman.BankEndDevelopment.Hangman;

public class StringConverter{

    public static String convertoCharacter (String word, String character){
        int lenOfWord = word.length();
        String guessedWord = helpWithFirstLetter(word);
        System.out.println("The chosen word has " + lenOfWord + " letters.");
        String gap = character;
        int i = 1;

        while(i < lenOfWord){
            guessedWord += gap;
            i++;
        }
        System.out.println(guessedWord);
        return guessedWord;
    }

    private static String helpWithFirstLetter(String word){
        char firstLetter = word.charAt(0);
        String guessedWord = "" + firstLetter;
        return guessedWord;
    }
}

