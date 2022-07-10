package com.example.progetto.Backend.Controllers;

import com.example.progetto.Backend.Entities.Artista;
import com.example.progetto.Backend.Services.ArtistaService;
import com.example.progetto.Backend.Support.Eccezioni.ArtistaEsistenteException;
import com.example.progetto.Backend.Support.Eccezioni.ArtistaInesistenteException;
import com.example.progetto.Backend.Support.Eccezioni.UtenteEsistenteException;
import com.example.progetto.Backend.Support.Messaggio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Impementata da Irtuso Remo
@Controller
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    /*
    Per convenzione utilizzo
    Post quando voglio aggiungere un oggetto
    Get quando lo voglio leggere
    Put quando lo voglio aggiornare
    Delete quando lo voglio eliminare
     */

    @PostMapping("/creaArtista")
    public ResponseEntity<Artista> creaArtista(@RequestBody @Valid Artista artista) {
        try {
            Artista nuovo = artistaService.registraArtista(artista);
            return new ResponseEntity<>(nuovo, HttpStatus.OK);
        } catch (ArtistaEsistenteException e) {
            return new ResponseEntity(new Messaggio("Artista gia registrato!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cercaArtista")
    public ResponseEntity<Artista> cercaArtista(@RequestParam(value = "codiceFiscale") String codiceFiscale){
        try{
            Artista risultato = artistaService.getArtista(codiceFiscale);
            return new ResponseEntity<>(risultato, HttpStatus.OK);
        }catch (ArtistaInesistenteException e) {
            return new ResponseEntity(new Messaggio("Artista non registrato"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("tuttiGliArtisti")
    List<Artista> restituisciTutti(){
        return artistaService.getAllArtisti();
    }
}
