package com.company.BackEndDevelopment.Hangman.GameManager;


import com.company.BackEndDevelopment.Hangman.ScoreManager.ScoreManagement;
import com.company.BackEndDevelopment.Hangman.SupplementaryClasses.Alphabets;
import com.company.BackEndDevelopment.Hangman.SupplementaryClasses.InputValidator;
import com.company.BackEndDevelopment.Hangman.SupplementaryClasses.ScannerInitiator;
import com.company.BackEndDevelopment.Hangman.SupplementaryClasses.StringConverter;
import com.company.BackEndDevelopment.Hangman.Voice.VoiceReader;
import com.company.BackEndDevelopment.Hangman.WordsManager.Manager.WordRandomizer;
import com.company.BackEndDevelopment.Login.DataBase.ManagingDataBase;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Hangman {
    ScannerInitiator scannerInitiator = new ScannerInitiator();
    final private String username;
    final private ScoreManagement scoreManagement;
    final private String password;
    final private Leaderboard leaderboard = new Leaderboard();

    public Hangman(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        scoreManagement = new ScoreManagement(username);
    }

    private void playGame () throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {

        System.out.println("\nLet's play!");
        WordRandomizer wordRandomizer = new WordRandomizer();
        int playerScore = 0;

            ArrayList<Character> remainingAlphabet = new ArrayList<>();

            for(char letter = 'A'; letter <= 'Z'; ++letter){
                remainingAlphabet.add(letter);
            }

            String randomWord = wordRandomizer.getRandomWord();
            String guessedWord = StringConverter.convertoCharacter(randomWord, "*");
            int lenOfRandomWord = randomWord.length();
            int numberOfFailedTries = 8;
            InputValidator inputValidator = new InputValidator();

            while(numberOfFailedTries > 0 ){
                String guessedLetter = inputValidator.getValidGuess();
                String guessedLetterUpperCase = guessedLetter.toUpperCase();
                Alphabets.printRemainingAlphabets(guessedLetterUpperCase.charAt(0), remainingAlphabet);
                int ifGuessTrue = 0;
                ArrayList <Integer> correctIndexes = new ArrayList <>();

                for (int letterNum = 0; letterNum < lenOfRandomWord; letterNum++ ){
                    if (guessedLetterUpperCase.charAt(0) == randomWord.charAt(letterNum)){
                        ifGuessTrue++;
                        correctIndexes.add(letterNum);
                    }
                }
                if (ifGuessTrue > 0){
                    playerScore = makeNonNegativeScore(playerScore) + 2;
                    for (int correctIndex : correctIndexes){
                        guessedWord = guessedWord.substring(0, correctIndex) + guessedLetterUpperCase +  guessedWord.substring(correctIndex + 1);
                    }
                } else{
                    HangmanDoom hangmanDoom = new HangmanDoom();
                    hangmanDoom.printhangmanDrawingWhenPlayerFails(numberOfFailedTries);
                    numberOfFailedTries--;
                    playerScore--;
                    playerScore = makeNonNegativeScore(playerScore);
                    printCorrectWordAfterEightTries(randomWord, numberOfFailedTries, wordRandomizer.getUserLanguage());
                }
                System.out.println("Guessed: " + guessedWord);

                if (randomWord.equals(guessedWord)){
                    VoiceReader.readingVoice("pass", wordRandomizer.getUserLanguage());
                    leaderboard.setSmileyResponse(0x1F973);
                    scoreManagement.gradeGameStatus(username, "pass");
                    break;
                }
                System.out.println("Hey " + username + ", you scored : " +  playerScore);
                scoreManagement.setCurrentScore(playerScore);
        }
        ManagingDataBase.addUserAndScore(username,password, scoreManagement);
    }


    private void gameIntro(){
        HangmanIntro hangmanIntro = new HangmanIntro();
    }

    private int makeNonNegativeScore(int playScore){
        return Math.max(playScore, 0);
    }


    private void quitTheGame(){
        System.out.println(username + ", you quit!!");
    }

    private void printCredits(){
        System.out.println("Ashraf, Faith, Hanen, Niyi, Shiva and little Sophie");
    }

    private void printTheInstructions(){
        HangmanIntro.printGameInstructions(username);
    }

    public void gameOverview() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        gameIntro();
        gameChoiceImplementer();
    }

    private void printCorrectWordAfterEightTries(String word, int numberOfFailedTries, String language) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if (numberOfFailedTries == 0){
            VoiceReader.readingVoice("fail", language);
            leaderboard.setSmileyResponse(0x1F631);
            scoreManagement.gradeGameStatus(username, "lost");
            System.out.println(username + ", the correct word is: " + word);
        }
    }

    private void gameChoiceImplementer() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        printTheInstructions();
        String chosenInstruction = getchosenInstruction();
        if (chosenInstruction.equals("P")){
            playGame();
            System.out.println("We hope you enjoyed the game, " + username);
            try {
                int currentScore = Integer.parseInt(scoreManagement.getScores(username).get(0));
                leaderboard.publishNewScore(username, currentScore);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gameChoiceImplementer();
        } else if (chosenInstruction.equals("I") ){
            printTheInstructions();
            gameChoiceImplementer();
        }else if (chosenInstruction.equals("C")){
            printCredits();
            gameChoiceImplementer();
        }else if (chosenInstruction.equals("Q")){
            quitTheGame();
        }
    }


    private String getchosenInstruction(){
        System.out.print("\nChoose from the instructions: ");
        String chosenInstruction = scannerInitiator.getScannerString();
        return chosenInstruction.toUpperCase();
    }
}