package com.edial.GestionAcademica.service;
import com.edial.GestionAcademica.entities.Profesores;
import com.edial.GestionAcademica.repositories.ProfesoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesoresService {

	private final ProfesoresRepository profesoresRepository;

	// Inyección de dependencias por constructor
	@Autowired
	public ProfesoresService(ProfesoresRepository profesoresRepository) {
		this.profesoresRepository = profesoresRepository;
	}

	/**
	 * Guarda un nuevo profesor.
	 * @param nuevoProfesores El objeto Profesor a guardar.
	 * @return El profesor guardado.
	 */
	public Profesores crearProfesor(Profesores nuevoProfesores) {
		// Lógica de negocio si es necesaria, por ejemplo, validaciones
		return profesoresRepository.save(nuevoProfesores);
	}

	/**
	 * Busca un profesor por su código único.
	 * @param codProfesor El código del profesor.
	 * @return Un Optional que contiene el profesor si se encuentra, o vacío si no.
	 */
	public Optional<Profesores> obtenerProfesorPorId(String codProfesor) {
		return profesoresRepository.findById(codProfesor);
	}

	/**
	 * Obtiene una lista de todos los profesores.
	 * @return Una lista de objetos Profesor.
	 */
	public List<Profesores> obtenerTodosLosProfesores() {
		return profesoresRepository.findAll();
	}

	/**
	 * Actualiza un profesor existente.
	 * @param profesoresActualizado El objeto Profesor con los datos actualizados.
	 * @return El profesor actualizado.
	 */
	public Profesores actualizarProfesor(Profesores profesoresActualizado) {
		// Se valida que el profesor exista antes de actualizar
		if (profesoresRepository.existsById(profesoresActualizado.getCodProfesor())) {
			return profesoresRepository.save(profesoresActualizado);
		}
		// Puedes lanzar una excepción si el profesor no existe
		return null;
	}

	/**
	 * Elimina un profesor por su código.
	 * @param codProfesor El código del profesor a eliminar.
	 */
	public void eliminarProfesor(String codProfesor) {
		profesoresRepository.deleteById(codProfesor);
	}
}