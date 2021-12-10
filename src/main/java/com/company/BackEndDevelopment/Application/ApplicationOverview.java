package com.company.BackEndDevelopment.Application;

import com.company.BackEndDevelopment.Hangman.GameManager.Hangman;
import com.company.BackEndDevelopment.Hangman.GameManager.Leaderboard;
import com.company.BackEndDevelopment.Login.LoginValidation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ApplicationOverview {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        LoginValidation loginValidation = new LoginValidation();
        loginValidation.flockingIntoSocialFolks();
        String username = loginValidation.getUsername();
        String password = loginValidation.getPassword();
        System.out.println("username: " + username);
        Hangman hangman = new Hangman(username, password);
        hangman.gameOverview();
        /*Leaderboard leaderboard = new Leaderboard();
        System.out.println(leaderboard.toPrintLeaderboard());*/
        /*http://localhost:8081/game.html*/
       // System.out.println("username data store after: " + loginValidation.getUsernamesData());

       /* Hangman hangman = new Hangman(username);
        hangman.gameOverview();*/

        /*/createGame?playerName=Oleniyi*/

        /*/createGame?playerName=Oleniyi&language=english*/


    }
}
