package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.repository.UsuarioRepository;
import com.exercicios.M1S11H2.services.CadernoService;
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
@RequestMapping("/caderno")
public class CadernoController {
    @Autowired
    CadernoService service;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<CadernoEntitie> listarTodos(JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        List<CadernoEntitie> listaCadernos = service.listarTodos();

        List<CadernoEntitie> listaCadernosDoUsuario = new ArrayList<>();

        for (CadernoEntitie cadernos : listaCadernos){
            if (cadernos.getId_usuario().equals(usuario)){
                listaCadernosDoUsuario.add(cadernos);
            }
        }
        return listaCadernosDoUsuario;
    }

    @GetMapping("/{id}")
    public Optional<CadernoEntitie> listarPorId(@PathVariable Long id, JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        Optional<CadernoEntitie> caderno = Optional.ofNullable(service.listarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        if (caderno.get().getId_usuario().getId() == usuario.get().getId()){
            return service.listarPorId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody CadernoEntitie cadernoEntitie) throws Exception {return service.salvar(cadernoEntitie);}

    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id, JwtAuthenticationToken jwt){

        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        Optional<CadernoEntitie> cadernoEntitie = Optional.ofNullable(service.listarPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        if (cadernoEntitie.get().getId_usuario().getId() == usuario.get().getId()){
            service.removerPorId(id);
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping
    public int atualizar(@RequestBody CadernoEntitie cadernoEntitie, JwtAuthenticationToken jwt){
        Optional<UsuarioEntitie> usuario = usuarioRepository.findById(Long.valueOf(jwt.getName()));

        CadernoEntitie caderno = service.listarPorId(cadernoEntitie.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (caderno.getId_usuario().getId() == usuario.get().getId()){
            return service.atualizar(caderno);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
