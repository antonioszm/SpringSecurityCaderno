package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.repository.UsuarioRepository;
import com.exercicios.M1S11H2.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotasController {
    @Autowired
    NotaService service;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<NotaEntitie> listarTodos(JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        List<NotaEntitie> listaNotas = service.listarTodos();

        List<NotaEntitie> listaNotasDoUsuario = new ArrayList<>();

        for (NotaEntitie notas : listaNotas){
            if (notas.getId_usuario().equals(usuario)){
                listaNotasDoUsuario.add(notas);
            }
        }
        return listaNotasDoUsuario;
    }

    @GetMapping("/{id}")
    public Optional<NotaEntitie> listarPorId(@PathVariable Long id, JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        Optional<NotaEntitie> notaEntitie = Optional.ofNullable(service.listarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        if (notaEntitie.get().getId_usuario().getId() == usuario.get().getId()){
            return service.listarPorId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody NotaEntitie notaEntitie) throws Exception {return service.salvar(notaEntitie);}

    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id, JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        Optional<NotaEntitie> notaEntitie = Optional.ofNullable(service.listarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        if (notaEntitie.get().getId_usuario().getId() == usuario.get().getId()){
            service.removerPorId(id);
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping
    public int atualizar(@RequestBody NotaEntitie notaEntitie, JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        NotaEntitie nota = service.listarPorId(notaEntitie.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (nota.getId_usuario().getId() == usuario.get().getId()){
            return service.atualizar(nota);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
