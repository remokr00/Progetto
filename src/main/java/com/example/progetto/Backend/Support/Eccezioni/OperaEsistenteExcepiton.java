package com.example.progetto.Backend.Support.Eccezioni;

public class OperaEsistenteExcepiton extends Exception{

    private final String msg = "Opera già esistente";

    public OperaEsistenteExcepiton(){
        super();
        System.out.println(msg);
    }
}
