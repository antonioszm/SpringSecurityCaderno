package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.services.CadernoService;
import com.exercicios.M1S11H2.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotasController {
    @Autowired
    NotaService service;

    @GetMapping
    public List<NotaEntitie> listarTodos(){return service.listarTodos();}

    @GetMapping("/{id}")
    public Optional<NotaEntitie> listarPorId(@PathVariable Long id){return service.listarPorId(id);}

    @PostMapping
    public NotaEntitie salvar(@RequestBody NotaEntitie notaEntitie){return service.salvar(notaEntitie);}

    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id){service.removerPorId(id);}

    @PutMapping
    public int atualizar(@RequestBody NotaEntitie notaEntitie){return service.atualizar(notaEntitie);}
}
