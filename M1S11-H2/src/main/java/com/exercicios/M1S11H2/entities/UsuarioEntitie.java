package com.exercicios.M1S11H2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuarioEntitie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
