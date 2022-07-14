package com.example.progetto.Backend.Entities;

//Implementata da Irtuso Remo

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data
@Table(name = "ordine", schema = "sito")
public class Ordine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice", nullable = false)
    private int codice;

    @Basic
    @Column(name = "utente", nullable = false, length = 15)
    private String utente;

    @Basic
    @Column(name = "opera", nullable = false, length = 50)
    private String opera;

    @Basic
    @Column(name = "nome_proprietario", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "cognome_proprietario", nullable = false, length = 50)
    private String cognome;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    @CreationTimestamp
    private Date data;

    @Basic
    @Column(name = "numero_carta", nullable = false, length = 20, unique = true)
    private String carta;

    @Basic
    @Column(name = "scadenza", nullable = false, length = 5)
    private String scadenza;

    @Basic
    @Column(name = "CVV", nullable = false, length = 3, unique = true)
    private String cvv;

    //definisco le relazioni
    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente acquirente;

    @OneToMany(mappedBy = "ordine")
    private List<Opera> prodotti;


}
