package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Repositories.OperaRepository;
import com.example.progetto.Backend.Repositories.OridneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class OrdineService {

    @Autowired
    private OridneRepository ordineRepository;

    @PersistenceContext
    private EntityManager entityManager;

}
