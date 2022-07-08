package com.example.progetto.Backend.Repositories;

import com.example.progetto.Backend.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//Implementata da Irtuso Remo
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    //ricerca avanzata per verificare gli utenti
    @Query("SELECT u "+
            "FROM Utente u "+
            "WHERE (u.nome LIKE: nome OR :nome IS NULL) AND"+
            "       (u.cognome LIKE: cognome OR :cognome IS NULL) AND"+
            "       (u.codiceFiscale LIKE: codiceFiscale OR :codiceFiscale IS NULL) AND"+
            "       (u.dataDiNascita LIKE: dataDiNascitae OR :dataDiNascita IS NULL) AND"+
            "       (u.mail LIKE: mail OR :mail IS NULL) "

    )
    List<Utente> advancedResearch(String nome, String cognome, String codiceFiscale, String dataDiNascita, String mail );

    //vediamo se esiste l'utente con quella mail
    boolean existsByMail(String mail);

    //vediamo se esiste un utente con quest amial
    Utente findByMailContaining(String mail);

}
