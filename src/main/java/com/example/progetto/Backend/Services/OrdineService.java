package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Entities.Opera;
import com.example.progetto.Backend.Support.Eccezioni.OrdineEsistenteException;
import com.example.progetto.Backend.Support.Eccezioni.OrdineInesistenteException;
import com.example.progetto.Backend.Support.Eccezioni.UtenteInesistenteExcepiton;
import com.example.progetto.Backend.Entities.Ordine;
import com.example.progetto.Backend.Entities.Utente;
import com.example.progetto.Backend.Repositories.OrdineRepository;
import com.example.progetto.Backend.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ordine creaOrdine(Ordine ordine) throws OrdineEsistenteException {
        if(ordineRepository.existsByCodice(ordine.getCodice())){
            throw new OrdineEsistenteException();
        }
        return ordineRepository.save(ordine);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ordine> getOrdine(Utente utente) throws UtenteInesistenteExcepiton {
        if(!utenteRepository.existsByMail(utente.getMail())){
            throw new UtenteInesistenteExcepiton();
        }
        return ordineRepository.findByUtente(utente);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminaOrdine(Ordine ordine) throws OrdineInesistenteException {
        if(!ordineRepository.existsByCodice(ordine.getCodice())){
            throw new OrdineInesistenteException();
        }
        ordineRepository.delete(ordine);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ordine aggiorna(Ordine ordine) throws OrdineInesistenteException {
        if(!ordineRepository.existsByCodice(ordine.getCodice())){
            ordineRepository.save(ordine);
        }
        return ordineRepository.save(ordine);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ordine> ricercaPerData(Date data){
        return ordineRepository.findByData(data);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Ordine> ricercaPerPeriodoDiAcquisto(Date inizio, Date fine, Utente utente){
        return ordineRepository.findByBuyerInPeriod(inizio, fine, utente);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Ordine ricercaPerOpera(Opera opera){
        return ordineRepository.findByOpera(opera);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Ordine ricercaPerCoidce(Integer codice){
        return ordineRepository.findByCodice(codice);
    }





}
