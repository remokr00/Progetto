package com.example.progetto.Backend.Support.Eccezioni;

public class ArtistaEsistenteException extends Exception{

    private final String msg = "Artista già esistente";

    public ArtistaEsistenteException(){
        super();
        System.out.println(msg);
    }
}
