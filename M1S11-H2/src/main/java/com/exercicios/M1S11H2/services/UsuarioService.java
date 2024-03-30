package com.exercicios.M1S11H2.services;

import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.repository.NotaRepository;
import com.exercicios.M1S11H2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
    @Autowired

    UsuarioRepository repository;
    public List<UsuarioEntitie> listarTodos(){return repository.findAll();}
    public Optional<UsuarioEntitie> listarPorId(Long id){return repository.findById(id);}
    public UsuarioEntitie salvar(UsuarioEntitie usuarioEntitie){
        return repository.save(usuarioEntitie);
    }
    public void removerPorId(Long id){ repository.deleteById(id);}

    public int atualizar(UsuarioEntitie usuarioEntitie){
        return repository.update(usuarioEntitie.getId(), usuarioEntitie.getNome(), usuarioEntitie.getSenha());
    }
}
