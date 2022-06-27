package com.example.progetto.Entities;

//Implementata da Irtuso Remo

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ordine", schema = "sito")
public class Ordine {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "codice", nullable = false)
    private int codice;

    @Basic
    @Column(name = "utente", nullable = false, length = 15)
    private String utente;

    @Basic
    @Column(name = "opera", nullable = false)
    private String opera;

    @Basic
    @Column(name = "nome_intestatario", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "cognome_intestatario", nullable = false, length = 50)
    private String cognome;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private String data;

    @Basic
    @Column(name = "numero_carta", nullable = false, length = 20)
    private String carta;

    @Basic
    @Column(name = "scadenza", nullable = false, length = 5)
    private String scadenza;

    @Basic
    @Column(name = "CVV", nullable = false, length = 3)
    private String cvv;

    //definisco le relazioni
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente acquirente;

    @ManyToOne
    @JoinColumn(name = "opera")
    private Opera prodotto;


}
