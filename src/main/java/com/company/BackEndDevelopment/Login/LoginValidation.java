package com.company.BackEndDevelopment.Login;

import com.company.BackEndDevelopment.Login.DataBase.ManagingDataBase;


import java.io.IOException;
import java.util.Scanner;

public class LoginValidation {

    //private Map<String, String> usernamesData = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    private void setUsername(String username) {
        this.username = username;
    }

    private void toSignUp() throws IOException, BadLoginDetails{
        System.out.println("Signing up!!!!!");

        username = LoginDetails.getUsername();
        if (!ManagingDataBase.isUserExists(username)) {
            password = LoginDetails.getPassword();
            setUsername(username);
        }else {
            System.out.println("The username already exists, you can sign in instead!!!");
            toSignIn();
        }
    }

    private void toSignIn() throws IOException, BadLoginDetails{
        System.out.println("Signing in!!!!!");

        username = LoginDetails.getUsername();
        if (ManagingDataBase.isUserExists(username)) {
            password = LoginDetails.getPassword();
            toCheckPassword(username, password);
            setUsername(username);
        }else {
            System.out.println("We do not have this username stored. Taking you back!!!");
            flockingIntoSocialFolks();
        }

    }

    private void toCheckPassword(String username, String password) throws IOException {
        int numberOfTries = 3;
        for (int i = numberOfTries; i > 0; i--){
            if (!password.equals(ManagingDataBase.getUsersPassword(username))){
                System.out.println("Password is wrong and you have " + (i-1) + " number of tries left");
                password = LoginDetails.getPassword();

                if (i == 1){
                    throw new BadLoginDetails("Password is wrong");
                }
            }
        }
    }

    public void flockingIntoSocialFolks() throws IOException {
        System.out.print("Please select 1 to sign in and other numbers or letters to sign up: ");
        String optionInputted = scanner.nextLine();
        returnFlockingOption(optionInputted);
    }

    private void returnFlockingOption(String optionInputted) throws IOException{
        if (optionInputted.equals("1")){
            toSignIn();
        }else{
            toSignUp();
        }
    }

}
