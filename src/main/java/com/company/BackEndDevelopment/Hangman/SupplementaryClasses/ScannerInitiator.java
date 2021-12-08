package com.company.BackEndDevelopment.Hangman.SupplementaryClasses;

import java.util.Scanner;

public class ScannerInitiator{

    Scanner scanner = new Scanner(System.in);

    public int getScannerInt(){
        return scanner.nextInt();
    }

    public String getScannerString(){
        return scanner.nextLine();
    }

}