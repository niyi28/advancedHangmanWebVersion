package com.company.advancedHangman.BankEndDevelopment.Hangman;

import com.company.advancedHangman.BankEndDevelopment.Hangman.Voice.VoiceReader;
import com.company.advancedHangman.BankEndDevelopment.Login.DataBase.ManagingDataBase;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Hangman {
    ScannerInitiator scannerInitiator = new ScannerInitiator();
    final private String username;
    final private ScoreManagement scoreManagement;
    final private String password;

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
                    playerScore = playerScore + 2;
                    for (int correctIndex : correctIndexes){
                        guessedWord = guessedWord.substring(0, correctIndex) + guessedLetterUpperCase +  guessedWord.substring(correctIndex + 1);
                    }
                    VoiceReader.readingVoice("pass", wordRandomizer.getUserLanguage());
                } else{
                    HangmanDoom hangmanDoom = new HangmanDoom();
                    hangmanDoom.printhangmanDrawingWhenPlayerFails(numberOfFailedTries);
                    numberOfFailedTries--;
                    playerScore --;
                    printCorrectWordAfterEightTries(randomWord, numberOfFailedTries, wordRandomizer.getUserLanguage());
                }
                System.out.println("Guessed: " + guessedWord);

                if (randomWord.equals(guessedWord)){
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


    private void quitTheGame(){
        System.out.println(username + ", you quit!!");
    }

    private void printCredits(){
        System.out.println("Ashraf, Hanen, Niyi, Shiva and little Sophie");
        System.out.println("Word Source: EF website, https://www.ef.com/wwen/english-resources/english-vocabulary/top-3000-words/");
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
            System.out.println(username + ", the correct word is: " + word + ". You killed the man for nothing, learn!!");
        }
        VoiceReader.readingVoice("fail", language);
    }

    private void gameChoiceImplementer() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        printTheInstructions();
        String chosenInstruction = getchosenInstruction();
        if (chosenInstruction.equals("P")){
            playGame();
            System.out.println("We hope you enjoyed the game, " + username);
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