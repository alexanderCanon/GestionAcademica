package com.edial.GestionAcademica.service;
import com.edial.GestionAcademica.entities.Estudiante;
import com.edial.GestionAcademica.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

	private final EstudianteRepository estudianteRepository;

	// Inyección de dependencias por constructor
	@Autowired
	public EstudianteService(EstudianteRepository estudianteRepository) {
		this.estudianteRepository = estudianteRepository;
	}

	/**
	 * Guarda un nuevo estudiante.
	 * @param nuevoEstudiante El objeto Estudiante a guardar.
	 * @return El estudiante guardado.
	 */
	public Estudiante crearEstudiante(Estudiante nuevoEstudiante) {
		return estudianteRepository.save(nuevoEstudiante);
	}

	/**
	 * Busca un estudiante por su carnet.
	 * @param carnet El carnet del estudiante.
	 * @return Un Optional que contiene el estudiante si se encuentra, o vacío si no.
	 */
	public Optional<Estudiante> obtenerEstudiantePorId(String carnet) {
		return estudianteRepository.findById(carnet);
	}

	/**
	 * Obtiene una lista de todos los estudiantes.
	 * @return Una lista de objetos Estudiante.
	 */
	public List<Estudiante> obtenerTodosLosEstudiantes() {
		return estudianteRepository.findAll();
	}

	/**
	 * Actualiza un estudiante existente.
	 * @param estudianteActualizado El objeto Estudiante con los datos actualizados.
	 * @return El estudiante actualizado.
	 */
	public Estudiante actualizarEstudiante(Estudiante estudianteActualizado) {
		if (estudianteRepository.existsById(estudianteActualizado.getCarnet())) {
			return estudianteRepository.save(estudianteActualizado);
		}
		return null;
	}

	/**
	 * Elimina un estudiante por su carnet.
	 * @param carnet El carnet del estudiante a eliminar.
	 */
	public void eliminarEstudiante(String carnet) {
		estudianteRepository.deleteById(carnet);
	}
}
