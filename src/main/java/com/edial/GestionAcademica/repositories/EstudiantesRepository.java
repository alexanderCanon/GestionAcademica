package com.edial.GestionAcademica.repositories;
import com.edial.GestionAcademica.entities.Estudiantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudiantesRepository extends JpaRepository<Estudiantes, String> {

}
