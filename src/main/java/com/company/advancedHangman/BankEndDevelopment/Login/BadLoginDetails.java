package com.company.advancedHangman.BankEndDevelopment.Login;

public class BadLoginDetails extends RuntimeException{
    final private String message;
    public BadLoginDetails(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
