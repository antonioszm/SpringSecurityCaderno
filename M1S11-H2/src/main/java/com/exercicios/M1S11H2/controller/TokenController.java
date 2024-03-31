package com.exercicios.M1S11H2.controller;

import com.exercicios.M1S11H2.repository.UsuarioRepository;
import com.exercicios.M1S11H2.requests.LoginRequest;
import com.exercicios.M1S11H2.requests.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UsuarioRepository usuarioRepository) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        var usuario = usuarioRepository.findByNome(loginRequest.nome());

        if (usuario == null || !usuario.isLoginCorreto(loginRequest, bCryptPasswordEncoder)){
            throw new Exception("usuario ou senha invalidos");
        }

        var agora = Instant.now();
        var expiraEm = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("BackEnd")
                .subject(String.valueOf(usuario.getId()))
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(expiraEm))
                .build();

        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwt, expiraEm));
    }
}
