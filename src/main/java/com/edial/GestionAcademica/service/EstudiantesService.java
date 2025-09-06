package com.edial.GestionAcademica.service;
import com.edial.GestionAcademica.entities.Estudiantes;
import com.edial.GestionAcademica.repositories.EstudiantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudiantesService {

	private final EstudiantesRepository estudiantesRepository;

	// Inyección de dependencias por constructor
	@Autowired
	public EstudiantesService(EstudiantesRepository estudiantesRepository) {
		this.estudiantesRepository = estudiantesRepository;
	}

	/**
	 * Guarda un nuevo estudiante.
	 * @param nuevoEstudiantes El objeto Estudiante a guardar.
	 * @return El estudiante guardado.
	 */
	public Estudiantes crearEstudiante(Estudiantes nuevoEstudiantes) {
		return estudiantesRepository.save(nuevoEstudiantes);
	}

	/**
	 * Busca un estudiante por su carnet.
	 * @param carnet El carnet del estudiante.
	 * @return Un Optional que contiene el estudiante si se encuentra, o vacío si no.
	 */
	public Optional<Estudiantes> obtenerEstudiantePorId(String carnet) {
		return estudiantesRepository.findById(carnet);
	}

	/**
	 * Obtiene una lista de todos los estudiantes.
	 * @return Una lista de objetos Estudiante.
	 */
	public List<Estudiantes> obtenerTodosLosEstudiantes() {
		return estudiantesRepository.findAll();
	}

	/**
	 * Actualiza un estudiante existente.
	 * @param estudiantesActualizado El objeto Estudiante con los datos actualizados.
	 * @return El estudiante actualizado.
	 */
	public Estudiantes actualizarEstudiante(Estudiantes estudiantesActualizado) {
		if (estudiantesRepository.existsById(estudiantesActualizado.getCarnet())) {
			return estudiantesRepository.save(estudiantesActualizado);
		}
		return null;
	}

	/**
	 * Elimina un estudiante por su carnet.
	 * @param carnet El carnet del estudiante a eliminar.
	 */
	public void eliminarEstudiante(String carnet) {
		estudiantesRepository.deleteById(carnet);
	}
}
