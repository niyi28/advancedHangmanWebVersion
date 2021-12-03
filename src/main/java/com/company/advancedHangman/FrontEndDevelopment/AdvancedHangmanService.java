package com.company.advancedHangman.FrontEndDevelopment;

import org.springframework.stereotype.Service;

@Service
public class AdvancedHangmanService {

    private String playerName;
    private String word;

    public String getPlayerName() {
        return playerName;
    }

    public void createGame(String playerName) {
        this.playerName = playerName;
        System.out.println("Game was created for player "+playerName);
        // select the word
        word = "Secret"; // should be some random algorithm
        // return the word?
    }

    public int getWordSize() {
        return word.length();
    }
}
