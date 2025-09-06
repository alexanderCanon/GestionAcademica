package com.edial.GestionAcademica.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Profesor {

	@Id
	@Column(name = "cod_profesor", length = 10)
	private String codProfesor;

	@Column(name = "nombre_completo", length = 100, nullable = false)
	private String nombreCompleto;

	@Column(name = "correo_electronico", length = 100, nullable = false, unique = true)
	private String correoElectronico;

	public String getCodProfesor() {
		return codProfesor;
	}

	public void setCodProfesor(String codProfesor) {
		this.codProfesor = codProfesor;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
