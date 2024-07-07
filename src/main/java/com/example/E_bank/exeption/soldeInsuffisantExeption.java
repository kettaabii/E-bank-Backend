package com.example.E_bank.exeption;

public class soldeInsuffisantExeption extends Exception {
    public soldeInsuffisantExeption(String ErrorMessage) {
        super(ErrorMessage);
    }
}
