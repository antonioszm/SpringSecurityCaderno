package com.exercicios.M1S11H2.repository;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CadernoRepository extends JpaRepository<CadernoEntitie, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Caderno SET nota = :nota, id_usuario = :id_usuario  WHERE id = :id", nativeQuery = true)
    int update(@Param("id") Long id,
               @Param("nota") int nota,
               @Param("id_usuario") long id_usuario
    );
}
