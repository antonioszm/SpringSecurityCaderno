package com.exercicios.M1S11H2.services;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.repository.CadernoRepository;
import com.exercicios.M1S11H2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadernoService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CadernoRepository repository;
    public List<CadernoEntitie> listarTodos(){return repository.findAll();}

    public Optional<CadernoEntitie> listarPorId(Long id){return repository.findById(id);}
    public ResponseEntity<?> salvar(CadernoEntitie cadernoEntitie) throws Exception {
        long usuarioId = cadernoEntitie.getId_usuario().getId();

        UsuarioEntitie usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new Exception(("Usuario com Id {" + usuarioId + "} não encontrado")));
        cadernoEntitie.setId_usuario(usuario);
        repository.save(cadernoEntitie);
        return ResponseEntity.ok().build();
    }
    public void removerPorId(Long id){ repository.deleteById(id);}

    public int atualizar(CadernoEntitie cadernoEntitie){
        return repository.update(cadernoEntitie.getId(), cadernoEntitie.getNome(), cadernoEntitie.getId_usuario());
    }
}
