package com.example.progetto.Entities;

//Implementata da Gallo Ilaria

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ooera", schema = "sito")
public class Opera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codice", nullable = false)
    private int codice;

    @Basic
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "descrizione", length = 500)
    private String descrizione;

    @Basic
    @Column(name = "prezzo", nullable = false)
    private float prezzo;

    @Basic
    @Column(name = "artista", nullable = false)
    private int artista;

    @Basic
    @Column(name = "tipologia", length = 20)
    private String tipologia;

    //definizione relazioni

    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Artista creatore;

    @ManyToOne
    @JoinColumn(name = "codice")
    private Ordine ordine;

}
