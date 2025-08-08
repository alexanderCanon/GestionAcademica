package com.edial.GestionAcademica.controller;
import com.edial.GestionAcademica.entities.Profesor;
import com.edial.GestionAcademica.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

	private final ProfesorService profesorService;

	@Autowired
	public ProfesorController(ProfesorService profesorService) {
		this.profesorService = profesorService;
	}

	/**
	 * Endpoint para crear un nuevo profesor.
	 * Mapea a la solicitud HTTP POST.
	 * URL: POST /api/profesores
	 */
	@PostMapping
	public ResponseEntity<Profesor> crearProfesor(@RequestBody Profesor nuevoProfesor) {
		Profesor profesorCreado = profesorService.crearProfesor(nuevoProfesor);
		return new ResponseEntity<>(profesorCreado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para obtener todos los profesores.
	 * Mapea a la solicitud HTTP GET.
	 * URL: GET /api/profesores
	 */
	@GetMapping
	public ResponseEntity<List<Profesor>> obtenerTodosLosProfesores() {
		List<Profesor> profesores = profesorService.obtenerTodosLosProfesores();
		return new ResponseEntity<>(profesores, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un profesor por su ID.
	 * Mapea a la solicitud HTTP GET.
	 * URL: GET /api/profesores/{codProfesor}
	 */
	@GetMapping("/{codProfesor}")
	public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable String codProfesor) {
		return profesorService.obtenerProfesorPorId(codProfesor)
				.map(profesor -> new ResponseEntity<>(profesor, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint para actualizar un profesor existente.
	 * Mapea a la solicitud HTTP PUT.
	 * URL: PUT /api/profesores/{codProfesor}
	 */
	@PutMapping("/{codProfesor}")
	public ResponseEntity<Profesor> actualizarProfesor(@PathVariable String codProfesor, @RequestBody Profesor profesorActualizado) {
		// Aseguramos que el ID del profesor a actualizar coincida con el del path
		profesorActualizado.setCodProfesor(codProfesor);
		Profesor resultado = profesorService.actualizarProfesor(profesorActualizado);
		if (resultado != null) {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Endpoint para eliminar un profesor por su ID.
	 * Mapea a la solicitud HTTP DELETE.
	 * URL: DELETE /api/profesores/{codProfesor}
	 */
	@DeleteMapping("/{codProfesor}")
	public ResponseEntity<Void> eliminarProfesor(@PathVariable String codProfesor) {
		if (profesorService.obtenerProfesorPorId(codProfesor).isPresent()) {
			profesorService.eliminarProfesor(codProfesor);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content para borrado exitoso
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
