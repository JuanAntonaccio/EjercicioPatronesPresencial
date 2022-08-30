package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
	public static Connection databaseConnection;
	static String usuario="PRJUL22";
	static String clave="PRJUL22";
	static String base="jdbc:oracle:thin:@localhost:1521:xe";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Se encontro driver");

			
			try {
				System.out.println("conectamos");
				databaseConnection = DriverManager.getConnection(base, usuario, clave);

			} catch (SQLException e) {
				System.out.println("No logramos conectar");

			}

		} catch (ClassNotFoundException e) {
			System.out.println("No tienes el driver en tu build");
			e.printStackTrace();

		}
	}
	
	public static Connection getConnection() {
		return databaseConnection;
	}


}
