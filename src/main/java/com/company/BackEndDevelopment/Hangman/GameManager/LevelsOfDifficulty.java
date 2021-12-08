package com.company.BackEndDevelopment.Hangman.GameManager;

import com.company.BackEndDevelopment.Hangman.SupplementaryClasses.ScannerInitiator;

import java.util.ArrayList;

public class LevelsOfDifficulty{

    private final ArrayList <String> gameLevels = new ArrayList <> ();
    ScannerInitiator scannerInitiator = new ScannerInitiator();

    public LevelsOfDifficulty (){
        gameLevels.add("Easy");
        gameLevels.add("Medium");
        gameLevels.add("Hard");
    }

    private void printGameLevels (){
        int levelNumber = 0;
        for (String gameLevel : gameLevels){
            levelNumber++;
            System.out.println("Choose level number, " + levelNumber + " for " + gameLevel + "level");
        }
    }

    private int chooseLevelNumber (){
        System.out.print("Choose your level number: " );
        int chosenLevelNumber = scannerInitiator.getScannerInt();
        return chosenLevelNumber;
    }

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