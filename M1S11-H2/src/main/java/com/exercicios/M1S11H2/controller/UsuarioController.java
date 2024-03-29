package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.services.NotaService;
import com.exercicios.M1S11H2.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @GetMapping
    public List<UsuarioEntitie> listarTodos(){return service.listarTodos();}

    @GetMapping("/{id}")
    public Optional<UsuarioEntitie> listarPorId(@PathVariable Long id){return service.listarPorId(id);}

    @PostMapping
    public UsuarioEntitie salvar(@RequestBody UsuarioEntitie usuarioEntitie){return service.salvar(usuarioEntitie);}

    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id){service.removerPorId(id);}

    @PutMapping
    public int atualizar(@RequestBody UsuarioEntitie usuarioEntitie){return service.atualizar(usuarioEntitie);}
}
