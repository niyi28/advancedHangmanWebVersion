package com.company.BackEndDevelopment.Hangman.SupplementaryClasses;// import java.util.Scanner;
import java.util.ArrayList;

public class InputValidator {

    ArrayList <String> addedLetters = new ArrayList <> ();
    ScannerInitiator scannerInitiator = new ScannerInitiator();

    public String getValidGuess(){
        String guessedLetter = getGuess();
        while(!isValidLength(guessedLetter) || !isValidLetter(guessedLetter) || !isNewLetter(guessedLetter)){
            System.out.println("NOT valid! Its either you put in an invalid letter or repeating an already used letter ");
            System.out.println("");
            guessedLetter = getGuess();
        }
        return guessedLetter;
    }

    private boolean checkIndex(String guessedLetter){
        if (guessedLetter.length() > 0){
            return true;
        }
        return false;
    }

    /*private boolean checkStringIndex(String guessedWord)*/

    private String getGuess(){
        System.out.print("Guess: ");
        return scannerInitiator.getScannerString();
    }

    private boolean isValidLength (String guessedLetter){
        return !(guessedLetter.length() > 1);
    }

    private boolean isValidLetter (String guessedLetter){
        return Character.isLetter(guessedLetter.charAt(0));
    }

    private boolean isNewLetter (String guessedLetter){
        boolean isLetterExisting = addedLetters.contains(guessedLetter);
        addedLetters.add(guessedLetter);
        return !isLetterExisting;
    }
}