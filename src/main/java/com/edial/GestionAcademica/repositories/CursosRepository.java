package com.edial.GestionAcademica.repositories;

import com.edial.GestionAcademica.entities.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, String> {

}
