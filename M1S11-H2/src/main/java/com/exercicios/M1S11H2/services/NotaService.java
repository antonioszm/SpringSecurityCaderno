package com.exercicios.M1S11H2.services;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.repository.CadernoRepository;
import com.exercicios.M1S11H2.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NotaService {
    @Autowired

    NotaRepository repository;
    public List<NotaEntitie> listarTodos(){return repository.findAll();}
    public Optional<NotaEntitie> listarPorId(Long id){return repository.findById(id);}
    public NotaEntitie salvar(NotaEntitie notaEntitie){
        return repository.save(notaEntitie);
    }
    public void removerPorId(Long id){ repository.deleteById(id);}

    public int atualizar(NotaEntitie notaEntitie){
        return repository.update(notaEntitie.getId(), notaEntitie.getTitle(), notaEntitie.getContent(),notaEntitie.getId_caderno(), notaEntitie.getId_usuario());
    }
}
