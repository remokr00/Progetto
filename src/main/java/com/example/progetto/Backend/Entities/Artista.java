package com.example.progetto.Backend.Entities;

//Implementata da Gallo Ilaria
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;

@Entity
@Data
@Table(name = "artista", schema = "sito")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

   //definizione relazioni
    @OneToMany(mappedBy = "creatore")
    private HashSet<Opera> opere = new HashSet<>();

}
