package com.edial.GestionAcademica.repositories;

import com.edial.GestionAcademica.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {

}
