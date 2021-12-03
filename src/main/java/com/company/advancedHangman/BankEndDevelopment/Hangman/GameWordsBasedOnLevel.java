package com.company.advancedHangman.BankEndDevelopment.Hangman;

import java.util.ArrayList;

public class GameWordsBasedOnLevel{

    WordsGenerator wordsGenerator = new WordsGenerator();
    LevelsOfDifficulty levelsOfDifficulty = new LevelsOfDifficulty();
    ArrayList <String> listOfWords;
    String difficultyLevel;
    String userLanguage = wordsGenerator.getUserLanguage();

    public String getUserLanguage() {
        return userLanguage;
    }

    public GameWordsBasedOnLevel(){
        ArrayList <String> listOfWords = wordsGenerator.getListOfWords();
        String difficultyLevel = levelsOfDifficulty.getGameLevel();
        this.listOfWords = listOfWords;
        this.difficultyLevel = difficultyLevel;
    }

    private String goingThroughListOfWords(){
        String gameWord = "";
        for (String word : listOfWords){
            gameWord = word;
        }
        return gameWord;
    }

    private ArrayList <String> removeSingleLetterWords(){
        ArrayList <String> gameValidWords = new ArrayList <> ();
        for (String word : listOfWords){
            if (word.length() > 1){
                gameValidWords.add(word);
            }
        }
        return gameValidWords;
    }

    public ArrayList <String> getWordsForChosenLevel(){
        ArrayList <String> gameValidWords = removeSingleLetterWords();
        ArrayList <String> gameWordsForChosenLevel = new ArrayList <> ();

        for (String word : gameValidWords){
            int wordLength = word.length();
            int easyWordsLengthMax = 4;
            int mediumWordsLengthMax = 6;
            int hardWordsLengthMax = 8;
            if (difficultyLevel.equals("Easy") && wordLength <= easyWordsLengthMax){
                gameWordsForChosenLevel.add(word);
            }
            else if (difficultyLevel.equals("Medium") && wordLength > easyWordsLengthMax && wordLength <= mediumWordsLengthMax){
                gameWordsForChosenLevel.add(word);
            }
            else if (difficultyLevel.equals("Hard") && wordLength > mediumWordsLengthMax && wordLength <= hardWordsLengthMax){
                gameWordsForChosenLevel.add(word);
            }
        }
        return gameWordsForChosenLevel;
    }
}