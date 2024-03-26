package com.exercicios.M1S11H2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CadernoEntitie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int nota;

    private long id_usuario;

}
