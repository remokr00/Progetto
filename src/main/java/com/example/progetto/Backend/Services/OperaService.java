package com.example.progetto.Backend.Services;

import com.example.progetto.Backend.Repositories.OperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class OperaService {

    @Autowired
    private OperaRepository operaRepository;

    @PersistenceContext
    private EntityManager entityManager;

}
