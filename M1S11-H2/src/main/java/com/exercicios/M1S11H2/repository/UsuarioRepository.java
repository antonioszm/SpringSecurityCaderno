package com.exercicios.M1S11H2.repository;

import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntitie, Long> {
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE Usuario SET nome = :nome  WHERE id = :id", nativeQuery = true)
//    int update(@Param("id") Long id,
//               @Param("nome") String nome
//    );
}
