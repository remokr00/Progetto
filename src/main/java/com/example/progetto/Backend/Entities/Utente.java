package com.example.progetto.Backend.Entities;

//Implementata da Irtuso Remo


import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "utente", schema = "sito")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente", nullable = false)
    private int idUtente;

    @Basic
    @Column(name = "codice_fiscale", nullable = false, length = 15)
    private String codiceFiscale;

    @Basic
    @Column(name = "nome",nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @Basic
    @Column(name = "data_di_nascita", nullable = false, length = 10)
    private String dataDiNascita;

    @Basic
    @Column(name = "indirizzo", length = 50)
    private String indirizzo;

    @Basic
    @Column(name = "e_mail", length = 50)
    private String mail;

    //definisco le relazioni
    @OneToMany(mappedBy = "acquirente")
    private Set<Ordine> ordini;

}
