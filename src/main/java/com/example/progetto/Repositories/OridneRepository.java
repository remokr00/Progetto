package com.example.progetto.Repositories;
//Implementata da Gallo Ilaria
import com.example.progetto.Entities.Opera;
import com.example.progetto.Entities.Ordine;
import com.example.progetto.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface OridneRepository extends JpaRepository<Ordine, Integer> {

    //troviamo gli acquisti dell'utente x
    List<Ordine> findByUtente(Utente utente);

    //troviamo gli ordini effettuati in data x
    List<Ordine> findByData(Date data);

    //troviamo gli acquisti fatti dall'utente x in data y
    @Query("SELECT o "+
            "FROM Ordine o "+
            "WHERE o.data > :startDate AND o.data < :deadline AND o.utente = :utente")
    List<Ordine> findByBuyerInPeriod(Date startDate, Date deadline, Utente utente);

    //troviamo gli ordini di una particolare opera
    List<Ordine> findByOpera(Opera opera);

    //troviamo gli ordini con un determinato codice
    Ordine findByCodice(Integer codice);


}
