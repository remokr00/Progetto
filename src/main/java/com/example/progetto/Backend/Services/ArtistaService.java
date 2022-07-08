package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Repositories.ArtistaRepository;
import com.example.progetto.Backend.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Implementata da Irtuso Remo

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @PersistenceContext
    private EntityManager entityManager;







}
