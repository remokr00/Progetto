package com.example.progetto.Backend.Controllers;

import com.example.progetto.Backend.Entities.Opera;
import com.example.progetto.Backend.Entities.Ordine;
import com.example.progetto.Backend.Entities.Utente;
import com.example.progetto.Backend.Services.OrdineService;
import com.example.progetto.Backend.Support.Eccezioni.OpereInesistenteException;
import com.example.progetto.Backend.Support.Eccezioni.OrdineEsistenteException;
import com.example.progetto.Backend.Support.Eccezioni.OrdineInesistenteException;
import com.example.progetto.Backend.Support.Eccezioni.UtenteInesistenteExcepiton;
import com.example.progetto.Backend.Support.Messaggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

//implementato da Irtuso Remo
@RestController
@RequestMapping("/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/crea_ordine")
   // @PreAuthorize("hasAuthority('utente_progetto')")
    public ResponseEntity<Ordine> creaOrdine(@RequestBody @Valid Ordine ordine){
        try {
            Ordine nuovo = ordineService.creaOrdine(ordine);
            return new ResponseEntity<>(nuovo, HttpStatus.OK);
        }catch (OrdineEsistenteException e){
            return new ResponseEntity(new Messaggio("Ordine già presente"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cerca_ordine_per_utente")
    public ResponseEntity<List<Ordine>> cercaOrdineUtenete(@RequestParam(value = "utente") Utente utente){
        try{
            List<Ordine> risultato = ordineService.getOrdine(utente);
            return new ResponseEntity<>(risultato, HttpStatus.OK);
        }catch (UtenteInesistenteExcepiton e){
            return new ResponseEntity(new Messaggio("Utente non esistente"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/elimina_ordine")
  //  @PreAuthorize("hasAuthority('utente_progetto')")
    public ResponseEntity<Messaggio> eliminaOrdine(Ordine ordine){
        try{
            ordineService.eliminaOrdine(ordine);
            return new ResponseEntity<>(new Messaggio("Ordine eliminato con successo"), HttpStatus.OK);
        }catch (OrdineInesistenteException e){
            return new ResponseEntity<>(new Messaggio("Ordine inesistente"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/aggiorna_ordine")
  //  @PreAuthorize("hasAuthority('utente_progetto')")
    public ResponseEntity<Ordine> modificaOrdine(@RequestBody @Valid Ordine ordine){
        try{
            Ordine aggiornato = ordineService.aggiorna(ordine);
            return new ResponseEntity<>(aggiornato, HttpStatus.OK);
        }catch (OrdineInesistenteException e){
            return new ResponseEntity(new Messaggio("Ordine inesistente"), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/ricerca_per_data")
    public ResponseEntity<List<Ordine>> ricercaPerData(@RequestParam(value = "data") Date data){
        List<Ordine> risultato = ordineService.ricercaPerData(data);
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }

    @GetMapping("/ricerca_per_periodo")
    public ResponseEntity<List<Ordine>> ricercaPerPeriodo(@RequestParam(value = "inizio") Date inizio, @RequestParam(value = "fine") Date fine, @RequestParam(value = "utente") Utente utente){
        List<Ordine> risultato = ordineService.ricercaPerPeriodoDiAcquisto(inizio, fine, utente);
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }

    @GetMapping("/ricerca_per_opera")
    public ResponseEntity<Ordine> ricercaPerOpera(Opera opera){
        Ordine risultato = ordineService.ricercaPerOpera(opera);
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }

    @GetMapping("/ricerca_per_codice")
    public ResponseEntity<Ordine> ricercaPerCodice(@RequestParam(value = "codice") Integer codice){
        Ordine risulutato = ordineService.ricercaPerCoidce(codice);
        return new ResponseEntity<>(risulutato, HttpStatus.OK);
    }




}
