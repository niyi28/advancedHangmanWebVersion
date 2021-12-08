package com.company.BackEndDevelopment.Hangman.WordsManager.Manager;


import com.company.BackEndDevelopment.Hangman.GameManager.GameWordsBasedOnLevel;

import java.util.ArrayList;

public class WordRandomizer{

    private GameWordsBasedOnLevel gameWordsBasedOnLevel = new GameWordsBasedOnLevel();
    private ArrayList <String> listOfWords;
    private int numberOfStoredWords;
    private String userLanguage = gameWordsBasedOnLevel.getUserLanguage();


    public WordRandomizer (){
        ArrayList <String> listOfWords = gameWordsBasedOnLevel.getWordsForChosenLevel();
        int numberOfStoredWords = listOfWords.size();
        this.listOfWords = listOfWords;
        this.numberOfStoredWords = numberOfStoredWords;
    }

    public int getRandomNumber(){
        int min = 0;
        int max = numberOfStoredWords;
        int randomNumber = (int)(Math.random()*((max-min)+1))+min;
        return randomNumber;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public String getRandomWord (){
        int randomNumber = getRandomNumber();
        //listOfWords.get(getRandomNumber(randomNumber))
        String randomWord = listOfWords.get(randomNumber);
        return randomWord;
    }

}


