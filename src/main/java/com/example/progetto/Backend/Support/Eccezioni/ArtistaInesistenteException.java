package com.example.progetto.Backend.Support.Eccezioni;

public class ArtistaInesistenteException extends Exception{

    private final String msg = "Artista non esistente";

    public ArtistaInesistenteException(){
        super();
        System.out.println(msg);
    }

}
