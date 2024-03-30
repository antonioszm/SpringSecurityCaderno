package com.exercicios.M1S11H2.repository;

import com.exercicios.M1S11H2.entities.CadernoEntitie;
import com.exercicios.M1S11H2.entities.NotaEntitie;
import com.exercicios.M1S11H2.entities.UsuarioEntitie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntitie, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Nota SET title = :title, content = :content, id_caderno = :id_caderno, id_usuario = :id_usuario  WHERE id = :id", nativeQuery = true)
    int update(@Param("id") Long id,
               @Param("title") String title,
               @Param("content") String content,
               @Param("id_caderno") CadernoEntitie id_caderno,
               @Param("id_usuario") UsuarioEntitie id_usuario
    );
}
