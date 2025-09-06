package com.edial.GestionAcademica.repositories;

import com.edial.GestionAcademica.entities.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesoresRepository extends JpaRepository<Profesores, String> {

}
