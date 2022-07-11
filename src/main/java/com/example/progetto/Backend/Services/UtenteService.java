package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Support.Eccezioni.UtenteEsistenteException;
import com.example.progetto.Backend.Entities.Utente;
import com.example.progetto.Backend.Repositories.UtenteRepository;
import com.example.progetto.Backend.Support.Eccezioni.UtenteInesistenteExcepiton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Implementato da Gallo Ilaria

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //restituisco un utente che contiene l'email
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Utente getUtente(String mail){
        return utenteRepository.findByMailContaining(mail);
    }

    //restituisco tutti gli utenti del sistema
    @Transactional(readOnly = true)
    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }

    //registro un utente
    @Transactional(propagation = Propagation.REQUIRES_NEW) //richiede una nuova transazione
    public Utente registraUtente(Utente utente) throws UtenteEsistenteException {
        if (utenteRepository.existsByMail(utente.getMail())){ //verifico se l'utente è gia esistente
            throw new UtenteEsistenteException();
        }
        return utenteRepository.save(utente);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminaUtente(Utente utente) throws UtenteInesistenteExcepiton{
        if(!utenteRepository.existsByMail(utente.getMail())){
            throw new UtenteInesistenteExcepiton();
        }
        utenteRepository.delete(utente);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Utente> ricercaAvanzata(String nome, String cognome, String codiceFiscale, String dataDiNascita, String mail){
        return utenteRepository.advancedResearch(nome, cognome, codiceFiscale, dataDiNascita, mail);
    }

}
