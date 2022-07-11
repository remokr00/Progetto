package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Support.Eccezioni.ArtistaEsistenteException;
import com.example.progetto.Backend.Entities.Artista;
import com.example.progetto.Backend.Repositories.ArtistaRepository;
import com.example.progetto.Backend.Support.Eccezioni.ArtistaInesistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Implementata da Irtuso Remo

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //restituisco un artista con quel codice fiscale
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Artista getArtista(String codiceFiscale) throws ArtistaInesistenteException {
        if(! artistaRepository.existsByCodiceFiscale(codiceFiscale)){
            throw new ArtistaInesistenteException();
        }
        return artistaRepository.findByCodiceFiscale(codiceFiscale);
    }

    //restituisco tutti gli artisti del sistema
    @Transactional(readOnly = true)
    public List<Artista> getAllArtisti(){
        return artistaRepository.findAll();
    }

    //registro un artista
    @Transactional(propagation = Propagation.REQUIRES_NEW) //richiede una nuova transazione
    public Artista registraArtista(Artista artista) throws ArtistaEsistenteException {
        if (artistaRepository.existsById(artista.getIdArtista())){ //verifico se l'utente è gia esistente
            throw new ArtistaEsistenteException();
        }
        return artistaRepository.save(artista);
    }

    //ricerca avanzata
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Artista> ricercaAvanzata(String nome, String cognome){
        return artistaRepository.advancedResearch(nome, cognome);
    }







}
