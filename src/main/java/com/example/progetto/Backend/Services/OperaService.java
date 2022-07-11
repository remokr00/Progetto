package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Entities.Artista;
import com.example.progetto.Backend.Support.Eccezioni.OperaEsistenteExcepiton;
import com.example.progetto.Backend.Support.Eccezioni.OpereInesistenteException;
import com.example.progetto.Backend.Entities.Opera;
import com.example.progetto.Backend.Repositories.OperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

//Implementato da Gallo Ilaria

@Service
public class OperaService {

    @Autowired
    private OperaRepository operaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Opera> mostraTutteLeOpere() { return operaRepository.findAll(); }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Opera aggiungiOpera(Opera opera) throws OperaEsistenteExcepiton {
        if(operaRepository.existsByCodiceOrNome(opera.getCodice(), opera.getNome())){
            throw new OperaEsistenteExcepiton();
       }
        return operaRepository.save(opera);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Opera aggiornaOpera(Opera opera) throws OpereInesistenteException {
        if(!operaRepository.existsByCodiceOrNome(opera.getCodice(), opera.getNome())){
            operaRepository.save(opera);
        }
        return operaRepository.save(opera);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rimuoviOpera(Integer codice) throws OpereInesistenteException {
        if(!operaRepository.existsByCodice(codice)){
            throw new OpereInesistenteException();
        }
        operaRepository.deleteByCodice(codice);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Opera> paginazione(int numeroPagine, int grandezzaPagina, String ordinaPer){
        Pageable pageable = PageRequest.of(numeroPagine, grandezzaPagina, Sort.by(ordinaPer));
        Page<Opera> risultato = operaRepository.findAll(pageable);
        if(risultato.hasContent()){
            return risultato.getContent();
        }
        else{
            return new LinkedList<>();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Opera> ricercaAvanzata(Integer codice, String nome, Artista creatore, String tipologia, Float prezzo1, Float prezzo2){
        List<Opera> risultato = operaRepository.advancedResearch(codice, nome, creatore, tipologia, prezzo1, prezzo2);
        return risultato;
    }


}
