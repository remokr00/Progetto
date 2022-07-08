package com.example.progetto.Backend.Eccezioni;

public class UtenteEsistenteException extends Exception{

    private final String msg = "Utente già esistente";

    public UtenteEsistenteException(){
        super();
        System.out.println(msg);
    }

}
