package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import DLL.Conexion;

public class Profesor extends Usuario {

	// GETTERS Y SETTERS

	public Profesor(int id, String nombre, String apellido, String email, String password, int dni,
			String tipousuario) {
		super(id, nombre, apellido, email, password, dni, tipousuario);

	}

	public Profesor(String nombre, String apellido, String email, String password, int dni, String tipousuario) {
		super(nombre, apellido, email, password, dni, tipousuario);

	}
	// CONEXION

	static Connection con = Conexion.getInstance().getConnection();

	// ELIMINAR EJERCICIO A LA BASE DE DATOS

	public static void EliminarEjercicio(Ejercicio ejercicio) {

		try {
			PreparedStatement statementusuario = con.prepareStatement("DELETE FROM `ejercicio` WHERE id_ejercicio = ? ",
					Statement.RETURN_GENERATED_KEYS);
			statementusuario.setInt(1, ejercicio.getId_ejercicio());
			int filas = statementusuario.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		}
	}

	// EDITAR EJERCICIO A LA BASE DE DATOS

	public static void UpdateEjercicio(Ejercicio ejercicio) {
		try {
			PreparedStatement statementejericio = con.prepareStatement(
					"UPDATE `ejercicio` SET `nombre_ejercicio` = ?, `musculo` = ?, `dificultad` = ?, `cant_repeticiones` = ?, `cant_series` = ? WHERE `id_ejercicio` = ?");
			statementejericio.setString(1, ejercicio.getNombre_ejercicio());
			statementejericio.setString(2, ejercicio.getMusculo());
			statementejericio.setString(3, ejercicio.getDificultad());
			statementejericio.setInt(4, ejercicio.getCant_repeticiones());
			statementejericio.setInt(5, ejercicio.getCant_series());
			statementejericio.setInt(6, ejercicio.getId_ejercicio());

			int filas = statementejericio.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// METODO PARA MOSTRAR PROFESORES

	public static LinkedList<Profesor> mostrarProfesores() {
		LinkedList<Profesor> profesores = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM profesor");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_profesor");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int dni = rs.getInt("dni");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profesores;
	}

}
