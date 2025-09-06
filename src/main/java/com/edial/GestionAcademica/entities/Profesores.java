package com.edial.GestionAcademica.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Profesores {

	@Id
	@Column(name = "cod_profesor", length = 10)
	private String codProfesor;

	@Column(name = "nombre_completo", length = 100, nullable = false)
	private String nombreCompleto;

	@Column(name = "correo_electronico", length = 100, nullable = false, unique = true)
	private String correoElectronico;

    @Column(name = "telefono", length = 10, nullable = false)
    private String telefono;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
