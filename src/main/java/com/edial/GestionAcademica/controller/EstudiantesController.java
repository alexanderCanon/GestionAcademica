package com.edial.GestionAcademica.controller;
import com.edial.GestionAcademica.entities.Estudiantes;
import com.edial.GestionAcademica.service.EstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesController {

	private final EstudiantesService estudiantesService;

	@Autowired
	public EstudiantesController(EstudiantesService estudiantesService) {
		this.estudiantesService = estudiantesService;
	}

	/**
	 * Endpoint para crear un nuevo estudiante.
	 * URL: POST /api/estudiantes
	 */
	@PostMapping
	public ResponseEntity<Estudiantes> crearEstudiante(@RequestBody Estudiantes nuevoEstudiantes) {
		Estudiantes estudiantesCreado = estudiantesService.crearEstudiante(nuevoEstudiantes);
		return new ResponseEntity<>(estudiantesCreado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para obtener todos los estudiantes.
	 * URL: GET /api/estudiantes
	 */
	@GetMapping
	public ResponseEntity<List<Estudiantes>> obtenerTodosLosEstudiantes() {
		List<Estudiantes> estudiantes = estudiantesService.obtenerTodosLosEstudiantes();
		return new ResponseEntity<>(estudiantes, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un estudiante por su carnet.
	 * URL: GET /api/estudiantes/{carnet}
	 */
	@GetMapping("/{carnet}")
	public ResponseEntity<Estudiantes> obtenerEstudiantePorId(@PathVariable String carnet) {
		return estudiantesService.obtenerEstudiantePorId(carnet)
				.map(estudiantes -> new ResponseEntity<>(estudiantes, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint para actualizar un estudiante existente.
	 * URL: PUT /api/estudiantes/{carnet}
	 */
	@PutMapping("/{carnet}")
	public ResponseEntity<Estudiantes> actualizarEstudiante(@PathVariable String carnet, @RequestBody Estudiantes estudiantesActualizado) {
		estudiantesActualizado.setCarnet(carnet);
		Estudiantes resultado = estudiantesService.actualizarEstudiante(estudiantesActualizado);
		if (resultado != null) {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Endpoint para eliminar un estudiante por su carnet.
	 * URL: DELETE /api/estudiantes/{carnet}
	 */
	@DeleteMapping("/{carnet}")
	public ResponseEntity<Void> eliminarEstudiante(@PathVariable String carnet) {
		if (estudiantesService.obtenerEstudiantePorId(carnet).isPresent()) {
			estudiantesService.eliminarEstudiante(carnet);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
