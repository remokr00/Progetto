package com.example.progetto.Repositories;

import com.example.progetto.Entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Implementato da Irtuso Remo
@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    //Ricerca avanzata per filtrare la ricerca degli artisti
    @Query("SELECT a " +
            "FROM Artista a " +
            "WHERE (a.nome LIKE: nome OR :nome IS NULL) AND " +
            " (a.cognome LIKE: cognome OR :cognome IS NULL)"
    )
    List<Artista> advancedResearch(String nome, String cognome);

    //query per verificare l'esistenza dell'artista
    boolean existByNomeAndCognome(String nome, String cognome);

    //query per la ricerca di informazioni nella bio, tipo data di nascita o città
    List<Artista> findByBioLike(String bio);

}
