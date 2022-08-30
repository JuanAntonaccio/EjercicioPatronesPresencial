package examen;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

public class DAOAlumno {
	
	// Defino unas constantes que no cambian durante la ejecución del programa
	
	private static final String LISTAR_ALUMNOS = "SELECT * FROM ALUMNOS";
		
	private static final String INSERTAR_ALUMNO = "INSERT INTO ALUMNOS (ID_ALUMNO, NOMBRE, FECHAING, LENGUAJE, ID_PROFESOR) VALUES (?,?,?,?,?)";
		
	private static final String ELIMINAR_ALUMNO = "DELETE FROM ALUMNOS WHERE ID_ALUMNO = ?";
		
	private static final String MODIFICAR_ALUMNO = "UPDATE ALUMNOS SET NOMBRE=?, FECHAING=?, LENGUAJE=?, ID_PROFESOR=? WHERE ID_ALUMNO = ?";
		
	private static final String MAX_ID="SELECT MAX(ID_ALUMNO)FROM ALUMNOS";
		
	private static final String BUSCAR_NOMBRE="SELECT * FROM ALUMNOS WHERE NOMBRE=?";
		
	private static final String BORRAR_TODO="DELETE FROM ALUMNOS";
	
	private static final String CONSULTA="SELECT COUNT(*) AS CANTIDAD FROM ALUMNOS WHERE LENGUAJE=?";
		
	//Método Listar Alumnos
		public static LinkedList<Alumno> listadoAlumno() {
			ResultSet resultado=null;
			LinkedList<Alumno> alumnos = new LinkedList<Alumno>();
			try {
				PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(LISTAR_ALUMNOS);
				resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
	            while (resultado.next()){
					  Alumno alum = getAlumnoFromResultSet(resultado);
					  alumnos.add(alum);		
									
				}
				return alumnos;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}		
			
		}
		
		

		//Método Insertar Alumno
			public static boolean insertarAlumno(Alumno a) {
				int resultado = 0;      
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(INSERTAR_ALUMNO);
			    	int idMaximo = obtenerID()+1; // Saber el máximo ID que tiene la tabla
					sentencia.setInt( 1, idMaximo); // Agregar un ID más grande que el que figura en la tabla
					
					sentencia.setString(2, a.getNombre());
					sentencia.setDate(3, Date.valueOf(a.getFechaing()));
					sentencia.setString(4, a.getLenguaje().name());
					sentencia.setInt(5, a.getProfesor().getId_profesor());
					
					resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
					
					return resultado>0;
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return false;
			}
			
			
			//Método Eliminar un Alumno
			public static boolean eliminarAlumno(Alumno a) {   
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(ELIMINAR_ALUMNO);
					sentencia.setInt( 1, a.getId_alumno()); // Trae el ID de esa clase que pasamos por parámetro
					
					sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra el alumno con el ID que yo elegí.
					return true;
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return false;
			}
			
			// Metodo Elimina todos los Alumnos
					public static boolean eliminarTodo() {   
						try {
							PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(BORRAR_TODO);
							 
							sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra todos los alumnos
							sentencia.close();
							return true;
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						return false;
					}
			
			//Método Modificar Alumno
					public static boolean modificarAlumno(Alumno a) {
						int resultado = 0;      
						try {
							PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(MODIFICAR_ALUMNO);
												
							sentencia.setString(1, a.getNombre());
							
							sentencia.setDate(2, Date.valueOf(a.getFechaing()));
							
							sentencia.setString(3, a.getLenguaje().name());
							
							sentencia.setInt(4, a.getProfesor().getId_profesor());
							
							sentencia.setInt(5, a.getId_alumno());
							
						
							
							resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
							return resultado>0;
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						return false;
					}
					
					// Metodo para obtener el ultimo idalumno en la tabla ingresado
			public static int obtenerID() {
					int res=0;
					ResultSet resultado = null;      
					try {
						PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(MAX_ID);
						resultado = sentencia.executeQuery();	
						    
						while (resultado.next()){				
							 res = resultado.getInt(1);
						}
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						
						
						return res;
					}
					
			// Buscar una Alumno x nombre
			public static Alumno buscarNombre(String nombre) {
				ResultSet resultado = null;
				Alumno resp = null;
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
					sentencia.setString(1,nombre);
					resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
					while (resultado.next()){				
						resp = getAlumnoFromResultSet(resultado);	
					}
					}catch(SQLException e) {
						e.printStackTrace();
					}		
					return resp;
					}
			
			public static int cantidad(String dato) {
				// dato recibido por parametro es el dato a buscar
				ResultSet resultado=null;
				int resul=0;
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(CONSULTA);
					sentencia.setString(1,dato);
					resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
					while (resultado.next()){				
						resul = resultado.getInt("CANTIDAD");	
					}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				return resul;
			}
					
		// Obtenemos un objeto del tipo Alumno que viene en un resultado de una consulta
		private static Alumno getAlumnoFromResultSet(ResultSet resultado) throws SQLException {
						
			int id = resultado.getInt("ID_ALUMNO");		
			String nombre = resultado.getString("NOMBRE");
			LocalDate fecha_ing = resultado.getDate("FECHAING").toLocalDate();
		    Tipo tipo = Tipo.valueOf(resultado.getString("LENGUAJE"));
		    Profesor prof =DAOProfesor.buscarProfesor(resultado.getInt("ID_PROFESOR"));
						
						
			Alumno alu = new Alumno(id, nombre, fecha_ing,tipo,prof );
						
			return alu;
			}
					
		

}
