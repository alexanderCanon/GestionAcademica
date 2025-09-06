package com.edial.GestionAcademica.service;
import com.edial.GestionAcademica.dto.CursosDTO;
import 	com.edial.GestionAcademica.entities.Cursos;
import com.edial.GestionAcademica.repositories.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursosService {

	private final CursosRepository cursosRepository;

	// Inyección de dependencias por constructor (IoC) uso de @Autowired explícito
	@Autowired
	public CursosService(CursosRepository cursosRepository) {
		this.cursosRepository = cursosRepository;
	}

    /**
     *
     * @param cursos de la Entity
     * @return Entity Cursos a CursoDTO
     */
    private CursosDTO convertirACursosDTO(Cursos cursos) {
        CursosDTO dto = new CursosDTO();
        dto.setCod_curso(cursos.getCodCurso());
        dto.setNombreMateria(cursos.getNombreMateria());
        dto.setCreditos(cursos.getCreditos());
        return dto;
    }

    /**
     * Obtiene una lista de todos los cursos.
     * @return Una lista de objetos Curso.
     */
    public List<CursosDTO> obtenerTodosLosCursos() {
        return cursosRepository.findAll().stream()
                .map(this::convertirACursosDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un curso por su código.
     * @param codCurso El código del curso.
     * @return Un Optional que contiene el curso si se encuentra, o vacío si no.
     */
    public Optional<CursosDTO> obtenerCursoPorId(String codCurso) {
        return cursosRepository.findById(codCurso)
                .map(this::convertirACursosDTO);
    }


	/**
	 * Guarda un nuevo curso.
	 * @param nuevoCursos El objeto Curso a guardar.
	 * @return El curso guardado.
	 */
	public Cursos crearCurso(Cursos nuevoCursos) {
		return cursosRepository.save(nuevoCursos);
	}

	/**
	 * Actualiza un curso existente.
	 * @param cursosActualizado El objeto Curso con los datos actualizados.
	 * @return El curso actualizado.
	 */
	public Cursos actualizarCurso(Cursos cursosActualizado) {
		if (cursosRepository.existsById(cursosActualizado.getCodCurso())) {
			return cursosRepository.save(cursosActualizado);
		}
		return null;
	}

	/**
	 * Elimina un curso por su código.
	 * @param codCurso El código del curso a eliminar.
	 */
	public void eliminarCurso(String codCurso) {
		cursosRepository.deleteById(codCurso);
	}
}
