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
        /*System.out.println("username: " + username);*/
        Hangman hangman = new Hangman(username, password);
        hangman.gameOverview();

        /*int[] surrogates = {0x1F601};
        String alienEmojiString = new String(surrogates, 0, surrogates.length);
        System.out.println(alienEmojiString);*/
       /* String y = Character.toString( 128_512);*/
    }
}
