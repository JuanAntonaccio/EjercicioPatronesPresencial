package examen;

import java.sql.Date;
import java.time.LocalDate;

public class Alumno {
	private int id_alumno;
	private String nombre;
	private LocalDate fechaing;
	private Tipo lenguaje;
	private Profesor profesor;
	
	// Creamos los constructores
	
	
	
	public Alumno() {
		
	}

	public Alumno( String nombre, LocalDate fechaing, Tipo lenguaje, Profesor profesor) {
		
		this.nombre = nombre;
		this.fechaing = fechaing;
		this.lenguaje = lenguaje;
		this.profesor = profesor;
	}
	
	

	public Alumno(int id_alumno, String nombre, LocalDate fechaing, Tipo lenguaje, Profesor profesor) {
	
		this.id_alumno = id_alumno;
		this.nombre = nombre;
		this.fechaing = fechaing;
		this.lenguaje = lenguaje;
		this.profesor = profesor;
	}

	public int getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaing() {
		return fechaing;
	}

	public void setFechaing(LocalDate fechaing) {
		this.fechaing = fechaing;
	}

	public Tipo getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(Tipo lenguaje) {
		this.lenguaje = lenguaje;
	}
	
	

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Alumno [id_alumno=" + id_alumno + ", nombre=" + nombre + ", fechaing=" + fechaing + ", lenguaje="
				+ lenguaje + ", profesor=" + profesor + "]";
	}

	
	
	
	
	
	
	
	

}
