package com.edial.GestionAcademica.controller;
import com.edial.GestionAcademica.entities.Profesores;
import com.edial.GestionAcademica.service.ProfesoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesoresController {

	private final ProfesoresService profesoresService;

	@Autowired
	public ProfesoresController(ProfesoresService profesoresService) {
		this.profesoresService = profesoresService;
	}

	/**
	 * Endpoint para crear un nuevo profesor.
	 * Mapea a la solicitud HTTP POST.
	 * URL: POST /api/profesores
	 */
	@PostMapping
	public ResponseEntity<Profesores> crearProfesor(@RequestBody Profesores nuevoProfesores) {
		Profesores profesoresCreado = profesoresService.crearProfesor(nuevoProfesores);
		return new ResponseEntity<>(profesoresCreado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para obtener todos los profesores.
	 * Mapea a la solicitud HTTP GET.
	 * URL: GET /api/profesores
	 */
	@GetMapping
	public ResponseEntity<List<Profesores>> obtenerTodosLosProfesores() {
		List<Profesores> profesores = profesoresService.obtenerTodosLosProfesores();
		return new ResponseEntity<>(profesores, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un profesor por su ID.
	 * Mapea a la solicitud HTTP GET.
	 * URL: GET /api/profesores/{codProfesor}
	 */
	@GetMapping("/{codProfesor}")
	public ResponseEntity<Profesores> obtenerProfesorPorId(@PathVariable String codProfesor) {
		return profesoresService.obtenerProfesorPorId(codProfesor)
				.map(profesores -> new ResponseEntity<>(profesores, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint para actualizar un profesor existente.
	 * Mapea a la solicitud HTTP PUT.
	 * URL: PUT /api/profesores/{codProfesor}
	 */
	@PutMapping("/{codProfesor}")
	public ResponseEntity<Profesores> actualizarProfesor(@PathVariable String codProfesor, @RequestBody Profesores profesoresActualizado) {
		// Aseguramos que el ID del profesor a actualizar coincida con el del path
		profesoresActualizado.setCodProfesor(codProfesor);
		Profesores resultado = profesoresService.actualizarProfesor(profesoresActualizado);
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
		if (profesoresService.obtenerProfesorPorId(codProfesor).isPresent()) {
			profesoresService.eliminarProfesor(codProfesor);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content para borrado exitoso
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
