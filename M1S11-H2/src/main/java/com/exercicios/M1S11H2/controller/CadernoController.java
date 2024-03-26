package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.services.CadernoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caderno")
public class CadernoController {
    @Autowired
    CadernoService service;

    @GetMapping
    public List<CadernoEntitie> listarTodos(){return service.listarTodos();}

    @GetMapping("/{id}")
    public Optional<CadernoEntitie> listarPorId(@PathVariable Long id){return service.listarPorId(id);}

    @PostMapping
    public CadernoEntitie salvar(@RequestBody CadernoEntitie cadernoEntitie){return service.salvar(cadernoEntitie);}

    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id){service.removerPorId(id);}

    @PutMapping
    public int atualizar(@RequestBody CadernoEntitie cadernoEntitie){return service.atualizar(cadernoEntitie);}
}
