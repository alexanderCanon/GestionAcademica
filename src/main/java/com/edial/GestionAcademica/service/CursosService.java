package com.edial.GestionAcademica.service;
import com.edial.GestionAcademica.entities.Curso;
import com.edial.GestionAcademica.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

	private final CursoRepository cursoRepository;

	// Inyección de dependencias por constructor
	@Autowired
	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	/**
	 * Guarda un nuevo curso.
	 * @param nuevoCurso El objeto Curso a guardar.
	 * @return El curso guardado.
	 */
	public Curso crearCurso(Curso nuevoCurso) {
		return cursoRepository.save(nuevoCurso);
	}

	/**
	 * Busca un curso por su código.
	 * @param codCurso El código del curso.
	 * @return Un Optional que contiene el curso si se encuentra, o vacío si no.
	 */
	public Optional<Curso> obtenerCursoPorId(String codCurso) {
		return cursoRepository.findById(codCurso);
	}

	/**
	 * Obtiene una lista de todos los cursos.
	 * @return Una lista de objetos Curso.
	 */
	public List<Curso> obtenerTodosLosCursos() {
		return cursoRepository.findAll();
	}

	/**
	 * Actualiza un curso existente.
	 * @param cursoActualizado El objeto Curso con los datos actualizados.
	 * @return El curso actualizado.
	 */
	public Curso actualizarCurso(Curso cursoActualizado) {
		if (cursoRepository.existsById(cursoActualizado.getCodCurso())) {
			return cursoRepository.save(cursoActualizado);
		}
		return null;
	}

	/**
	 * Elimina un curso por su código.
	 * @param codCurso El código del curso a eliminar.
	 */
	public void eliminarCurso(String codCurso) {
		cursoRepository.deleteById(codCurso);
	}
}
