package examen;



public class Profesor {
	
	private int id_profesor;
	private String nombre;
	private String telefono;
	
	
	// constructores
	public Profesor(int id_profesor, String nombre, String telefono) {
		this.id_profesor = id_profesor;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public Profesor() {
		
	}

	public int getId_profesor() {
		return id_profesor;
	}

	public void setId_profesor(int id_profesor) {
		this.id_profesor = id_profesor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Profesor [id_profesor=" + id_profesor + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	}
	
	

}
