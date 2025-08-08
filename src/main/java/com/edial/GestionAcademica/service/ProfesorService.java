package com.edial.GestionAcademica.service;
import com.edial.GestionAcademica.entities.Profesor;
import com.edial.GestionAcademica.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

	private final ProfesorRepository profesorRepository;

	// Inyección de dependencias por constructor
	@Autowired
	public ProfesorService(ProfesorRepository profesorRepository) {
		this.profesorRepository = profesorRepository;
	}

	/**
	 * Guarda un nuevo profesor.
	 * @param nuevoProfesor El objeto Profesor a guardar.
	 * @return El profesor guardado.
	 */
	public Profesor crearProfesor(Profesor nuevoProfesor) {
		// Lógica de negocio si es necesaria, por ejemplo, validaciones
		return profesorRepository.save(nuevoProfesor);
	}

	/**
	 * Busca un profesor por su código único.
	 * @param codProfesor El código del profesor.
	 * @return Un Optional que contiene el profesor si se encuentra, o vacío si no.
	 */
	public Optional<Profesor> obtenerProfesorPorId(String codProfesor) {
		return profesorRepository.findById(codProfesor);
	}

	/**
	 * Obtiene una lista de todos los profesores.
	 * @return Una lista de objetos Profesor.
	 */
	public List<Profesor> obtenerTodosLosProfesores() {
		return profesorRepository.findAll();
	}

	/**
	 * Actualiza un profesor existente.
	 * @param profesorActualizado El objeto Profesor con los datos actualizados.
	 * @return El profesor actualizado.
	 */
	public Profesor actualizarProfesor(Profesor profesorActualizado) {
		// Se valida que el profesor exista antes de actualizar
		if (profesorRepository.existsById(profesorActualizado.getCodProfesor())) {
			return profesorRepository.save(profesorActualizado);
		}
		// Puedes lanzar una excepción si el profesor no existe
		return null;
	}

	/**
	 * Elimina un profesor por su código.
	 * @param codProfesor El código del profesor a eliminar.
	 */
	public void eliminarProfesor(String codProfesor) {
		profesorRepository.deleteById(codProfesor);
	}
}