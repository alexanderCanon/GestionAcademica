package com.edial.GestionAcademica.repositories;

import com.edial.GestionAcademica.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {

}
