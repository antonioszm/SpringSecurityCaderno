package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import com.exercicios.M1S11H2.repository.UsuarioRepository;
import com.exercicios.M1S11H2.requests.InserirUsuarioRequest;
import com.exercicios.M1S11H2.services.NotaService;
import com.exercicios.M1S11H2.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService service;
    @Autowired
    UsuarioRepository repository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<UsuarioEntitie> listarTodos(){return service.listarTodos();}

    @GetMapping("/{id}")
    public Optional<UsuarioEntitie> listarPorId(@PathVariable Long id){return service.listarPorId(id);}

    @PostMapping
    public UsuarioEntitie salvar(@RequestBody UsuarioEntitie usuarioEntitie){return service.salvar(usuarioEntitie);}
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody InserirUsuarioRequest inserirUsuarioRequest) throws Exception {
        UsuarioEntitie usuarioEmUso = repository.findByNome(inserirUsuarioRequest.nome());
        if (usuarioEmUso != null){
            throw new Exception("Usuario em uso");
        }

        UsuarioEntitie usuario = new UsuarioEntitie();
        usuario.setNome(inserirUsuarioRequest.nome());
        usuario.setSenha(bCryptPasswordEncoder.encode(inserirUsuarioRequest.senha().toString()));
        service.salvar(usuario);
        return new ResponseEntity<>("Criado", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id){service.removerPorId(id);}

    @PutMapping
    public int atualizar(@RequestBody UsuarioEntitie usuarioEntitie){return service.atualizar(usuarioEntitie);}
}
