package com.company.advancedHangman.BankEndDevelopment.Login;

public class BadChoiceInput extends RuntimeException{
    private final String message;
    public BadChoiceInput(){
         this.message = "Wrong input, please choose between 1 and 2!!!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
