package com.example.progetto.Backend.Controllers;

import com.example.progetto.Backend.Entities.Utente;
import com.example.progetto.Backend.Services.UtenteService;
import com.example.progetto.Backend.Support.Eccezioni.UtenteEsistenteException;
import com.example.progetto.Backend.Support.Eccezioni.UtenteInesistenteExcepiton;
import com.example.progetto.Backend.Support.Messaggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.ListUI;
import javax.validation.Valid;
import java.util.List;
//Implementato da Gallo Ilaria
@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @GetMapping("/ricerca_utente_mail")
    public ResponseEntity<List<Utente>> ricercsUtente(@RequestParam(value = "mail") String mail){
        List<Utente> risultato = utenteService.getUtente(mail);
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }

    @GetMapping("/tutti_gli_utenti")
    public ResponseEntity<List<Utente>> tuttiGliUtenti(){
        List<Utente> risultato = utenteService.getAllUtenti();
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }

    @PostMapping("/crea_utente")
    public ResponseEntity<Utente> registraUtente(@RequestBody @Valid Utente utente){
        try{
            Utente nuovo = utenteService.registraUtente(utente);
            return new ResponseEntity<>(nuovo, HttpStatus.OK);
        }catch (UtenteEsistenteException e){
            return new ResponseEntity(new Messaggio("Utente già esistente"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/elimina_utente")
    //@PreAuthorize("hasAuthority('amministratore_progetto')")
    public ResponseEntity<Messaggio> eliminaUtente(@RequestBody @Valid Utente utente){
        try{
            utenteService.eliminaUtente(utente);
            return new ResponseEntity<>(new Messaggio("Utente eliminato con successo"), HttpStatus.OK);
        }catch (UtenteInesistenteExcepiton e){
            return new ResponseEntity<>(new Messaggio("Utente inesistente"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ricerca_avanzata")
    public ResponseEntity<List<Utente>> ricercaAvanzata(@RequestParam(value = "nome", required = false)String nome,@RequestParam(value = "cognome", required = false)String cognome, @RequestParam(value = "codiceFiscale", required = false)String codiceFiscale, @RequestParam(value = "dataDiNascita", required = false)String dataDiNascita,@RequestParam(value = "mail", required = false) String mail){
        List<Utente> risultato = utenteService.ricercaAvanzata(nome, cognome, codiceFiscale, dataDiNascita, mail);
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }
}
