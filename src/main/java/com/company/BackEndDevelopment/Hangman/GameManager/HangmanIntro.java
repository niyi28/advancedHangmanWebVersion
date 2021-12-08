package com.company.BackEndDevelopment.Hangman.GameManager;

public class HangmanIntro{

    public static void printGameInstructions(String username){
        System.out.println("INSTRUCTIONS============");
        System.out.println("1: HANGAMN IS THE GAME WHERE YOU HAVE TO GUESS THE WORD.");
        System.out.println("2: YOU WILL HAVE TO GUESS THE MAXIMUM WORDS FOR THE MAXIMUM SCORE.");
        System.out.println("3: EVERY LEVEL WILL HAVE THE NEW WORD .");
        System.out.println("4: 1 CORRECT LETTER = 1 SCORE");
        System.out.println("5: YOU HAVE THE n CHOICES TO FIND THE MAXIMUM WORDS");

        System.out.println("HOW TO PLAY \n ===========");
        System.out.println(username + ", use your keyboard and guess each letter in the word. \nIf you guess right, you get two points and if you guess wrong, you loose a point. BEST OF LUCK !");
        System.out.println("Press: P for PLAY");
        System.out.println("Press: I for INSTRUCTIONS");
        System.out.println("Press: C for CREDITS");
        System.out.println("Press: Q for QUIT");
    }

    /*public void printGameIntro(){
        System.out.println("--------------------------------------------");
        System.out.println("| #  #   #   #   #  #### #   #   #   #   # |");
        System.out.println("| #  #  # #  ##  # #     ## ##  # #  ##  # |");
        System.out.println("| #### ##### # # # #  ## # # # ##### # # # |");
        System.out.println("| #  # #   # #  ## #   # #   # #   # #  ## |");
        System.out.println("| #  # #   # #   #  ###  #   # #   # #   # |");
        System.out.println("--------------------------------------------");
    }*/
}