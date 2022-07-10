package com.example.progetto.Backend.Entities;

//Implementata da Gallo Ilaria
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "artista", schema = "sito")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista", nullable = false)
    private int idArtista;

    @Basic
    @Column(name = "codice_fiscale", nullable = false, length = 15)
    private String codiceFiscale;

    @Basic
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = false, length = 50)
    private String cognome;

    @Basic
    @Column(name = "bio", length = 500)
    private String bio;


    //Il cascade sarebbe la propagazione delle azioni da un entity verso quelle "figlie" diciamo.
    //Con delete ad esempio se eliminato il creatore vengono eliminate tutte le opere ecc.
    //definizione relazioni
    @OneToMany(mappedBy = "creatore", cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Opera> opere;

}
