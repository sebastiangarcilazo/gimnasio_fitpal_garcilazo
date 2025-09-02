package DLL;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Conexion {
	private static String URL ="jdbc:mysql://localhost:3306/fitpal";
	private static String USER = "root";
	private static String PASSWORD ="";
	
	private static Connection connect;
	private static Conexion instance;
	private Conexion() {
		try {
			connect =  (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
		}
	}
	public static Conexion getInstance() {
		if(instance ==null) {
			instance = new Conexion();
		}
		return instance;	
	}
	public Connection getConnection() {
		return connect;
	}
	
}
