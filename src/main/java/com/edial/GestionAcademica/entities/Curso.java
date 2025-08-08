package com.edial.GestionAcademica.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Curso {

	@Id
	@Column(name = "cod_curso", length = 10)
	private String codCurso;

	@Column(name = "nombre_materia", length = 100, nullable = false)
	private String nombreMateria;

	@Column(name = "creditos", nullable = false)
	private Integer creditos;

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
}
