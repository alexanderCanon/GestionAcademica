package com.edial.GestionAcademica.controller;
import com.edial.GestionAcademica.entities.Estudiante;
import com.edial.GestionAcademica.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

	private final EstudianteService estudianteService;

	@Autowired
	public EstudianteController(EstudianteService estudianteService) {
		this.estudianteService = estudianteService;
	}

	/**
	 * Endpoint para crear un nuevo estudiante.
	 * URL: POST /api/estudiantes
	 */
	@PostMapping
	public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante nuevoEstudiante) {
		Estudiante estudianteCreado = estudianteService.crearEstudiante(nuevoEstudiante);
		return new ResponseEntity<>(estudianteCreado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para obtener todos los estudiantes.
	 * URL: GET /api/estudiantes
	 */
	@GetMapping
	public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantes() {
		List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
		return new ResponseEntity<>(estudiantes, HttpStatus.OK);
	}

	/**
	 * Endpoint para obtener un estudiante por su carnet.
	 * URL: GET /api/estudiantes/{carnet}
	 */
	@GetMapping("/{carnet}")
	public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable String carnet) {
		return estudianteService.obtenerEstudiantePorId(carnet)
				.map(estudiante -> new ResponseEntity<>(estudiante, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint para actualizar un estudiante existente.
	 * URL: PUT /api/estudiantes/{carnet}
	 */
	@PutMapping("/{carnet}")
	public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable String carnet, @RequestBody Estudiante estudianteActualizado) {
		estudianteActualizado.setCarnet(carnet);
		Estudiante resultado = estudianteService.actualizarEstudiante(estudianteActualizado);
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
		if (estudianteService.obtenerEstudiantePorId(carnet).isPresent()) {
			estudianteService.eliminarEstudiante(carnet);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
