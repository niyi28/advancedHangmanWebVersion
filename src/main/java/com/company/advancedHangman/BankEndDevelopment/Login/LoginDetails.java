package com.company.advancedHangman.BankEndDevelopment.Login;
import java.util.Scanner;

public class LoginDetails {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getUsername() throws BadLoginDetails {
        System.out.print("Please put in your username: ");
        String userName = scanner.nextLine().toLowerCase();
        validatingUsername(userName);
        return userName;

    }

    private static void validateAgainstOnlyIntegers(String userName){
        if (isInteger(userName)) {
            throw new BadLoginDetails("You inputted only integers as your user name. Please this is not allowed");
        }
    }

    private static void validateAgainstSpecialLetters(String userName){
        if (!checkForLettersAndPoint(userName)) {
            throw new BadLoginDetails("You inputted special letters. Please this is not allowed");
        }
    }

    private static void validateAgainstSingleUsername(String userName){
        if (userName.length() < 2) {
            throw new BadLoginDetails("You inputted just one letter as your username. Please this is not allowed");
        }
    }

    private static void validatingUsername(String userName){
        validateAgainstOnlyIntegers(userName);
        validateAgainstSpecialLetters(userName);
        validateAgainstSingleUsername(userName);
    }


    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean checkForLettersAndPoint(String s) {
        for (String character: s.split("")){
            if (!character.matches("[a-zA-Z]+") && !character.equals(".")) {
                return false;
            }
        }
        return true;
    }

    public static String getPassword(){
        /*"hh"*/
        System.out.print("Please put in your secure password: ");
        return scanner.nextLine();
    }

}
