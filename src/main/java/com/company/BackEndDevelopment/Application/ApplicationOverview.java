package com.company.BackEndDevelopment.Application;

import com.company.BackEndDevelopment.Hangman.GameManager.Hangman;
import com.company.BackEndDevelopment.Hangman.GameManager.Leaderboard;
import com.company.BackEndDevelopment.Login.LoginValidation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class ApplicationOverview {

    public static void main(String[] args) throws IOException {
        LoginValidation loginValidation = new LoginValidation();
        try {
            loginValidation.flockingIntoSocialFolks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = loginValidation.getUsername();
        String password = loginValidation.getPassword();
        Hangman hangman = null;
        try {
            hangman = new Hangman(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            hangman.gameOverview();
        } catch (IOException | UnsupportedAudioFileException |
                LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
        /*Leaderboard leaderboard = new Leaderboard();
        System.out.println(leaderboard.toPrintLeaderboard());*/
    }
}
