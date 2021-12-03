package com.company.advancedHangman.BankEndDevelopment.Hangman;

import com.company.advancedHangman.BankEndDevelopment.Login.DataBase.ManagingDataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreManagement {

    private int currentAvg = -1;
    private int currentScore;
    private final int numberOfUsers = ManagingDataBase.getDatabaseSize();
    private int bestScore = 0;
    private final String username;


    public ScoreManagement(String username) throws IOException {
        setBestScore(username);
        this.username = username;
    }

    public void setBestScore(String username) throws IOException {
        this.bestScore = Integer.parseInt(ManagingDataBase.deriveUserScoreSheet(username).get(1));;
    }

    public int selectBestScore(int currentScore) throws IOException {
        return Math.max(currentScore, bestScore);
    }

    public int getAverageScore(String username) throws IOException {
        bestScore = selectBestScore(currentScore);
        currentAvg =  ((currentAvg*(numberOfUsers-1)/numberOfUsers) +
                        (bestScore/numberOfUsers));
        return currentAvg;
    }

    public void setCurrentScore(int currentScore) throws IOException {
        this.currentScore = currentScore;
        bestScore = selectBestScore(currentScore);
        System.out.println("current score: " + currentScore);
        System.out.println("best score: " + bestScore);
    }

    public List<String> getScores(String username) throws IOException {
        List <String> scores = new ArrayList<>();
        if (username.equals(this.username)){
            scores.add(String.valueOf(currentScore));
            scores.add(String.valueOf(bestScore));
        }else{
            scores.add(ManagingDataBase.deriveUserScoreSheet(username).get(0));
            scores.add(ManagingDataBase.deriveUserScoreSheet(username).get(1));
        }
        return scores;
    }
}
