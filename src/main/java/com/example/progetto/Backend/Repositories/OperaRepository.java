package com.example.progetto.Backend.Repositories;

//Implementata da Gallo Ilaria
import com.example.progetto.Backend.Entities.Artista;
import com.example.progetto.Backend.Entities.Opera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperaRepository extends JpaRepository<Opera, Integer> {

    //Ricerca avanzata per l'opera
    @Query(" SELECT o "+
            "FROM Opera o "+
            "WHERE (o.codice = :codice OR :codice IS NULL) AND"+
            "      (o.nome LIKE :nome OR :nome IS NULL) AND"+
            "      (o.creatore = :creatore OR :creatore IS NULL) AND"+
            "      (o.tipologia LIKE :tipologia OR :tipologia IS NULL) AND"+
            "      (o.prezzo >= :prezzo1  AND o.prezzo < :prezzo2 OR (:prezzo1  IS NULL OR :prezzo2 IS NULL) ) AND " +
            "      ( (o.prezzo >= :prezzo1 ) OR :prezzo1 IS NULL ) AND " +
            "      ( (o.prezzo <= :prezzo2 ) OR :prezzo2 IS NULL ) "

    )
    List<Opera> advancedResearch(Integer codice, String nome, Artista creatore, String tipologia, Float prezzo1, Float prezzo2);


    //verifico se esiste l'opera
    boolean existsByCodiceOrNome(Integer codice, String nome);

    //verifica se l'opera esite a seconda del codice che passiamo
    boolean existsByCodice(Integer integer);

    //vediamo se esiste un opera con quel nome
    boolean existsByNome(String nome);

    //eliminiamo l'opera contenente quel codice
    void deleteByCodice(Integer codice);

    //liste di opere il cui nome contiene una determinata serie di caratteri
    List<Opera> findByNomeContaining(String nome);

    //troviamo le opere legate ad un determinato artista
    List<Opera> findByCreatore(Artista artista);

    //elimino le opere dell'artista x
    void deleteByCreatore(Artista artista);



}
