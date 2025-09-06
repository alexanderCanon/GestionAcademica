package com.edial.GestionAcademica.controller;
import com.edial.GestionAcademica.dto.CursosDTO;
import com.edial.GestionAcademica.entities.Cursos;
import com.edial.GestionAcademica.service.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursosController {

	private final CursosService cursosService;

    // Inyección de dependencias por constructor (IoC) uso de @Autowired explícito
	@Autowired
	public CursosController(CursosService cursosService) {
		this.cursosService = cursosService;
	}

    /**
     * Convierte una Entidad de Curso a un DTO de Curso.
     */
    private CursosDTO convertirACursosDTO(Cursos cursos) {
        CursosDTO dto = new CursosDTO();
        dto.setCod_curso(cursos.getCodCurso());
        dto.setNombreMateria(cursos.getNombreMateria());
        dto.setCreditos(cursos.getCreditos());
        return dto;
    }

    /**
     * Mapea un DTO de Curso a una entidad de Curso.
     */
    private Cursos convertirACursosEntity(CursosDTO dto) {
        Cursos entidad = new Cursos();
        entidad.setCodCurso(dto.getCod_curso());
        entidad.setNombreMateria(dto.getNombreMateria());
        entidad.setCreditos(dto.getCreditos());
        return entidad;
    }

	/**
	 * Endpoint para obtener todos los cursos.
	 * URL: GET /api/cursos
	 */
	@GetMapping
	public ResponseEntity<List<CursosDTO>> obtenerTodosLosCursos() {
		List<CursosDTO> cursos = cursosService.obtenerTodosLosCursos();
		return new ResponseEntity<>(cursos, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un curso por su código.
	 * URL: GET /api/cursos/{codCurso}
     * Ahora devuelve un DTO
	 */
	@GetMapping("/{codCurso}")
	public ResponseEntity<CursosDTO> obtenerCursoPorId(@PathVariable String codCurso) {
		return cursosService.obtenerCursoPorId(codCurso)
				.map(cursosDTO -> new ResponseEntity<>(cursosDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    /**
     * Endpoint para crear un nuevo curso.
     * URL: POST /api/cursos
     */
    @PostMapping
    public ResponseEntity<CursosDTO> crearCurso(@RequestBody CursosDTO nuevoCursosDTO) {
        // 1. Se recibe el DTO del curso y se mapea a una entidad
        Cursos nuevoCurso = convertirACursosEntity(nuevoCursosDTO);
        // 2. Se llama al servicio para guardar la entidad
        Cursos cursoCreado = cursosService.crearCurso(nuevoCurso);
        // 3. Entidad guardada mapeo a DTO para respuesta
        CursosDTO cursoCreadoDTO = convertirACursosDTO(cursoCreado);
        return new ResponseEntity<>(cursoCreadoDTO, HttpStatus.CREATED);
    }

	/**
	 * Endpoint para actualizar un curso existente.
     * Recibe un DTO, convierte a Entity y luego actualiza
	 * URL: PUT /api/cursos/{codCurso}
	 */
	@PutMapping("/{codCurso}")
	public ResponseEntity<CursosDTO> actualizarCurso(@PathVariable String codCurso,
                                                     @RequestBody CursosDTO cursosActualizadoDTO) {
        //Controller debería recibir un DTO y luego convertirlo a Entity y luego llamar al servicio
        //Esto para mantener la capa de servicio limpia de DTOS
        //1. Mapeo DTO a Entity
		Cursos cursosActualizado = convertirACursosEntity(cursosActualizadoDTO);
        cursosActualizado.setCodCurso(codCurso);
        //2. Se llama al servicio para actualizar la Entity
		Cursos resultado = cursosService.actualizarCurso(cursosActualizado);
        //3. Verificar el resultado
		if (resultado != null) {
            //4. Si fue actualizado, se mapea la Entity a DTO  y se devuelve
			return new ResponseEntity<>(convertirACursosDTO(resultado), HttpStatus.OK);
		}
        // 5. Si no se encontró el curso, devolvemos 404 NOT FOUND.
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Endpoint para eliminar un curso por su código.
	 * URL: DELETE /api/cursos/{codCurso}
	 */
	@DeleteMapping("/{codCurso}")
	public ResponseEntity<Void> eliminarCurso(@PathVariable String codCurso) {
        // 1. Verificamos si el curso existe antes de intentar eliminar.
		if (cursosService.obtenerCursoPorId(codCurso).isPresent()) {
			cursosService.eliminarCurso(codCurso);
            // 2. Devolvemos 204 NO CONTENT para indicar éxito sin cuerpo de respuesta.
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
        // 3. Si no existe, devolvemos 404 NOT FOUND.
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
