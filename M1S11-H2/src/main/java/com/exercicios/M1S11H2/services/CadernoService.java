package com.exercicios.M1S11H2.services;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.repository.CadernoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadernoService {
    @Autowired

    CadernoRepository repository;
    public List<CadernoEntitie> listarTodos(){return repository.findAll();}

    public Optional<CadernoEntitie> listarPorId(Long id){return repository.findById(id);}
    public CadernoEntitie salvar(CadernoEntitie cadernoEntitie){
        return repository.save(cadernoEntitie);
    }
    public void removerPorId(Long id){ repository.deleteById(id);}

    public int atualizar(CadernoEntitie cadernoEntitie){
        return repository.update(cadernoEntitie.getId(), cadernoEntitie.getNota(), cadernoEntitie.getId_usuario());
    }
}
