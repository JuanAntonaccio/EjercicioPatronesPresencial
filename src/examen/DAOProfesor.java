package examen;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAOProfesor {
	
	// Defino una constante que no cambia durante la ejecución del programa
	private static final String LISTAR_PROFESORES = "SELECT * FROM PROFESORES";
		
	private static final String INSERTAR_PROFESOR = "INSERT INTO PROFESORES (ID_PROFESOR, NOMBRE, TELEFONO) VALUES (?,?,?)";
		
	private static final String ELIMINAR_PROFESOR = "DELETE FROM PROFESORES WHERE ID_PROFESOR = ?";
		
	private static final String MODIFICAR_PROFESOR = "UPDATE PROFESORES SET NOMBRE=?, TELEFONO=? WHERE ID_PROFESOR =?";
		
	private static final String MAX_ID="SELECT MAX(ID_PROFESOR)FROM PROFESORES";
		
	private static final String BUSCAR_NOMBRE="SELECT * FROM PROFESORES WHERE NOMBRE=?";
		
	private static final String BORRAR_TODO="DELETE FROM PROFESORES";
	
	private static final String BUSCAR_PROFESOR="SELECT * FROM PROFESORES WHERE ID_PROFESOR=?";
	

		//Método Listar Profesores
	
		public static ResultSet listadoTotal() {
			ResultSet resultado=null;
			
			try {
				PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(LISTAR_PROFESORES);
				resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
	            		
									
				
				return resultado;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}		
			
		}
		
		

		//Método Insertar Profesor
			public static boolean insertarProfesor(Profesor p) {
				int resultado = 0;      
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(INSERTAR_PROFESOR);
			    	int idmax = obtenerID()+1; // Saber el máximo ID que tiene la tabla
					sentencia.setInt( 1, idmax); // Agregar un ID más grande que el que figura en la tabla
					
					sentencia.setString(2, p.getNombre());
					sentencia.setString(3, p.getTelefono());
					

					
					resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
					
					return resultado>0;
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return false;
			}
			
			
			//Método Eliminar un Profesor
			public static boolean eliminarProfesor(Profesor p) {   
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(ELIMINAR_PROFESOR);
					sentencia.setInt( 1, p.getId_profesor()); // Trae el ID de esa clase que pasamos por parámetro
					
					sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra la mascota con el ID que yo elegí.
					return true;
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return false;
			}
			
			// Metodo Eliminar todas los Profesores
					public static boolean eliminarTodo() {   
						try {
							PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(BORRAR_TODO);
							 
							sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra todos las mascotas
							sentencia.close();
							return true;
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						return false;
					}
			
			//Método Modificar Profesor
					public static boolean modificarProfesor(Profesor p) {
						int resultado = 0;      
						try {
							PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(MODIFICAR_PROFESOR);
												
							sentencia.setString(1, p.getNombre());	
							sentencia.setString(2, p.getTelefono());
							sentencia.setInt(3, p.getId_profesor());
							
							resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
							return resultado>0;
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						return false;
					}
					
					// Metodo para obtener el ultimo idprofesor en la tabla ingresado
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
					
			// Buscar una Profesor x nombre
			public static Profesor buscarNombre(String nombre) {
				ResultSet resultado = null;
				Profesor resp = null;
				try {
					PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
					sentencia.setString(1,nombre);
					resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
					while (resultado.next()){				
						resp = getProfesorFromResultSet(resultado);	
					}
					}catch(SQLException e) {
						e.printStackTrace();
					}		
					return resp;
					}
			
		// Buscar un profesor por id
	    public static Profesor buscarProfesor(int id) {
	    	ResultSet resultado = null;
			Profesor resp = null;
			try {
				PreparedStatement sentencia = DataBaseManager.getConnection().prepareStatement(BUSCAR_PROFESOR);
				sentencia.setInt(1,id);
				resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve UN PROFESOR en ésta variable de nombre resultado.
				while (resultado.next()){				
					resp = getProfesorFromResultSet(resultado);	
				}
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return resp;
				}
		
	    
					
		// Obtenemos un objeto del tipo Profesor que viene en un resultado de una consulta
		private static Profesor getProfesorFromResultSet(ResultSet resultado) throws SQLException {
						
			int idProfesor = resultado.getInt("ID_PROFESOR");		
			String nombre = resultado.getString("NOMBRE");
			String tele = resultado.getString("TELEFONO");
			
						
						
			Profesor profe = new Profesor(idProfesor, nombre, tele );
						
            return profe;
			}
					
		

}
