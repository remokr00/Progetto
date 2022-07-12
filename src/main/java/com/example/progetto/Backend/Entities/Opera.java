package com.example.progetto.Backend.Entities;

//Implementata da Gallo Ilaria

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "opera", schema = "sito")
public class Opera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista creatore;

    @ManyToOne
    @JoinColumn(name = "related_ordine")
    private Ordine ordine;

}
