package com.edial.GestionAcademica.repositories;
import com.edial.GestionAcademica.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

}
