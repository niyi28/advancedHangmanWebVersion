package com.company.advancedHangman.BankEndDevelopment.Hangman;

import java.util.ArrayList;

public class LevelsOfDifficulty{

    private final ArrayList <String> gameLevels = new ArrayList <> ();
    ScannerInitiator scannerInitiator = new ScannerInitiator();

    public LevelsOfDifficulty (){
        gameLevels.add("Easy");
        gameLevels.add("Medium");
        gameLevels.add("Hard");
    }



    // public InputValidator (){
    //   this.scannerInitiator = scannerInitiator;
    // }

    private void printGameLevels (){
        int levelNumber = 0;
        for (String gameLevel : gameLevels){
            levelNumber++;
            System.out.println("Choose level number, " + levelNumber + " for " + gameLevel + "level");
        }
    }

    private int chooseLevelNumber (){
        // Scanner scanner = getScanner();
        System.out.print("Choose your level number: " );
        int chosenLevelNumber = scannerInitiator.getScannerInt();
        return chosenLevelNumber;
    }

    // private Scanner getScanner (){
    //     Scanner scanner = new Scanner(System.in);
    //     return scanner;
    // }

    public String getGameLevel(){
        printGameLevels();
        int chosenLevelNumber = chooseLevelNumber();
        chosenLevelNumber--;
        String gameLevel = gameLevels.get(chosenLevelNumber);
        printGameLevel(gameLevel);
        return gameLevel;
    }

    private void printGameLevel (String gameLevel){
        System.out.println("You have chosen: " + gameLevel);
    }

}