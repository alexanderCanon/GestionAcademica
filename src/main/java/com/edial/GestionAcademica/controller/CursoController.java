package com.edial.GestionAcademica.controller;
import com.edial.GestionAcademica.entities.Curso;
import com.edial.GestionAcademica.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

	private final CursoService cursoService;

	@Autowired
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	/**
	 * Endpoint para crear un nuevo curso.
	 * URL: POST /api/cursos
	 */
	@PostMapping
	public ResponseEntity<Curso> crearCurso(@RequestBody Curso nuevoCurso) {
		Curso cursoCreado = cursoService.crearCurso(nuevoCurso);
		return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para obtener todos los cursos.
	 * URL: GET /api/cursos
	 */
	@GetMapping
	public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
		List<Curso> cursos = cursoService.obtenerTodosLosCursos();
		return new ResponseEntity<>(cursos, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un curso por su código.
	 * URL: GET /api/cursos/{codCurso}
	 */
	@GetMapping("/{codCurso}")
	public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable String codCurso) {
		return cursoService.obtenerCursoPorId(codCurso)
				.map(curso -> new ResponseEntity<>(curso, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint para actualizar un curso existente.
	 * URL: PUT /api/cursos/{codCurso}
	 */
	@PutMapping("/{codCurso}")
	public ResponseEntity<Curso> actualizarCurso(@PathVariable String codCurso, @RequestBody Curso cursoActualizado) {
		cursoActualizado.setCodCurso(codCurso);
		Curso resultado = cursoService.actualizarCurso(cursoActualizado);
		if (resultado != null) {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Endpoint para eliminar un curso por su código.
	 * URL: DELETE /api/cursos/{codCurso}
	 */
	@DeleteMapping("/{codCurso}")
	public ResponseEntity<Void> eliminarCurso(@PathVariable String codCurso) {
		if (cursoService.obtenerCursoPorId(codCurso).isPresent()) {
			cursoService.eliminarCurso(codCurso);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
