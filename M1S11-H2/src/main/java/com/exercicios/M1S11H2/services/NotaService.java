package com.exercicios.M1S11H2.services;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.repository.CadernoRepository;
import com.exercicios.M1S11H2.repository.NotaRepository;
import com.exercicios.M1S11H2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {
    @Autowired
    NotaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CadernoRepository cadernoRepository;


    public List<NotaEntitie> listarTodos(){return repository.findAll();}
    public Optional<NotaEntitie> listarPorId(Long id){return repository.findById(id);}
    public ResponseEntity<?> salvar(NotaEntitie notaEntitie) throws Exception {
        long usuarioId = notaEntitie.getId_usuario().getId();

        UsuarioEntitie usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new Exception(("Usuario com Id {" + usuarioId + "} não encontrado")));
        notaEntitie.setId_usuario(usuario);

        long cadernoId = notaEntitie.getId_caderno().getId();

        CadernoEntitie caderno = cadernoRepository.findById(cadernoId).orElseThrow(() -> new Exception(("Caderno com Id {" + cadernoId + "} não encontrado")));
        notaEntitie.setId_caderno(caderno);

        repository.save(notaEntitie);

        return ResponseEntity.ok().build();
    }
    public void removerPorId(Long id){ repository.deleteById(id);}

    public int atualizar(NotaEntitie notaEntitie){
        return repository.update(notaEntitie.getId(), notaEntitie.getTitle(), notaEntitie.getContent(),notaEntitie.getId_caderno(), notaEntitie.getId_usuario());
    }
}
