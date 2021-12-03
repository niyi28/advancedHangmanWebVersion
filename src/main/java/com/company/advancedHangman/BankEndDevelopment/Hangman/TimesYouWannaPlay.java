package com.company.advancedHangman.BankEndDevelopment.Hangman;

public class TimesYouWannaPlay{

    public static int numOfPlayingTimes(){
        System.out.println("\nHow many times do you want to play? (not more than 10 times)");
        ScannerInitiator scannerInitiator = new ScannerInitiator();
        System.out.print("The imputed number of playing is = ");
        int numOfPlayingUser = scannerInitiator.getScannerInt();

        while(numOfPlayingUser > 10){
            System.out.println("The number of games is limited! So please enter a number less than 10!\n");
            System.out.print("The imputed number of playing is = ");
            numOfPlayingUser = scannerInitiator.getScannerInt();
        }
        return numOfPlayingUser;
    }

    public static String printTimesPlayed(int playedtimes){
        String timesPlayed = "";

        if (playedtimes == 1){
            timesPlayed = "first";
        }else if (playedtimes == 2){
            timesPlayed = "second";
        }else if (playedtimes == 3){
            timesPlayed = "third";
        }else if (playedtimes == 4){
            timesPlayed = "fourth";
        }else if (playedtimes == 5){
            timesPlayed = "fifth";
        }else if (playedtimes == 6){
            timesPlayed = "sixth";
        }else if (playedtimes == 7){
            timesPlayed = "seventh";
        }else if (playedtimes == 8){
            timesPlayed = "eigth";
        }else if (playedtimes == 9){
            timesPlayed = "ninth";
        }else if (playedtimes == 10){
            timesPlayed = "tenth";
        }
        return timesPlayed;
    }
}