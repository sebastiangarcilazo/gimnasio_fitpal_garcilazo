package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import DLL.Conexion;
import repository.Encriptador;

public class Administrador extends Usuario {
	static LinkedList<String> historial = new LinkedList<>();
	public Administrador(int id, String nombre, String apellido, String email, String contrasena, int dni,
			String tipousuario) {
		super(id, nombre, apellido, email, contrasena, dni, tipousuario);
	}

	// GETTERS Y SETTERS

	// CONEXION

	static Connection con = Conexion.getInstance().getConnection();
	
	// ELIMINAR USUARIO DE LA BASE DE DATOS

	public static void EliminarUsuario(Usuario usuario) {
		try {
			if (usuario.getTipousuario().equalsIgnoreCase("Profesor")) {
				PreparedStatement statementdelprofesor = con
						.prepareStatement("DELETE FROM profesor WHERE id_usuario = ?", Statement.RETURN_GENERATED_KEYS);
				statementdelprofesor.setInt(1, usuario.getId());
				statementdelprofesor.executeUpdate();
			}
			if (usuario.getTipousuario().equalsIgnoreCase("Cliente")) {
				PreparedStatement statementdelcliente = con
						.prepareStatement("DELETE FROM cliente WHERE id_usuario = ?;", Statement.RETURN_GENERATED_KEYS);
				statementdelcliente.setInt(1, usuario.getId());
				statementdelcliente.executeUpdate();
			}
			PreparedStatement statementusuario = con.prepareStatement("DELETE FROM usuario WHERE id_usuario = ? ",
					Statement.RETURN_GENERATED_KEYS);
			statementusuario.setInt(1, usuario.getId());
			statementusuario.executeUpdate();
			historial.add(" Usuario eliminado ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// UPDATE USUARIO

	public static void UpdateUsuario(Usuario usuario) {
		try {
			PreparedStatement statementusuario = con.prepareStatement(
					"UPDATE `usuario` SET `nombre` = ?, `apellido` = ?, `email` = ?, `password` = ?, `dni` = ?, `tipousuario` = ? WHERE `id_usuario` = ?");
			statementusuario.setString(1, usuario.getNombre());
			statementusuario.setString(2, usuario.getApellido());
			statementusuario.setString(3, usuario.getEmail());
			statementusuario.setString(4, usuario.getPassword());
			statementusuario.setInt(5, usuario.getDni());
			statementusuario.setString(6, usuario.getTipousuario());
			statementusuario.setInt(7, usuario.getId());

			int filas = statementusuario.executeUpdate();
			historial.add(" Usuario actualizado ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// AGREGA EL PROFESORES A LA BASE DE DATOS

	public static void agregarProfesor(Profesor profesor, Usuario profesor2) {
		try {
			PreparedStatement stmtProfesor = con.prepareStatement("INSERT INTO profesor (id_usuario) VALUES (?)");
			stmtProfesor.setInt(1, profesor2.getId());
			int filasAfectadas = stmtProfesor.executeUpdate();
			historial.add(" Profesor Agregado a la BD ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// METODO PARA BUSCAR CLIENTE

	public static Cliente BuscarCliente(String email, String password) {
		Cliente cliente = null;
		try {
			String passwordencriptada = Encriptador.encriptar(password);
			String statementusuario = "SELECT * FROM Usuario WHERE email = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(statementusuario);
			ps.setString(1, email);
			ps.setString(2, passwordencriptada);

			ResultSet rs2 = ps.executeQuery();
			int id_usuario = 0;
			int dni = 0;
			String nombre = "";
			String apellido = "";
			if (rs2.next()) {
				id_usuario = rs2.getInt("id_usuario");
				nombre = rs2.getString("nombre");
				apellido = rs2.getString("apellido");
				dni = rs2.getInt("dni");
			}
			if (id_usuario != 0) {
				String statementcliente = "SELECT * FROM Cliente WHERE id_usuario = ?";
				PreparedStatement ps2 = con.prepareStatement(statementcliente);
				ps2.setInt(1, id_usuario);
				ResultSet rs = ps2.executeQuery();
				if (rs.next()) {
					cliente = new Cliente(rs.getInt("id_cliente"), nombre, apellido, email, password, dni,
							rs.getString("tiposubs"), rs.getString("subsestado"), "");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;
	}

	// AGREGA EL CLIENTE A LA BASE DE DATOS

	public static void agregarCliente(Cliente cliente, Usuario cliente2) {
		try {
			PreparedStatement stmtCliente = con.prepareStatement(
					"INSERT INTO cliente (id_usuario, tiposubs, subsestado) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmtCliente.setInt(1, cliente2.getId());
			stmtCliente.setString(2, cliente.getTiposubs());
			stmtCliente.setString(3, cliente.getSubsestado());

			int filas = stmtCliente.executeUpdate();

			if (filas > 0) {
				ResultSet rs = stmtCliente.getGeneratedKeys();
				if (rs.next()) {
					int id_cliente = rs.getInt(1);
					cliente.setId_cliente(id_cliente);
					historial.add(" Cliente Agregado a la BD ");
				}
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// AÑADIR EJERCICIO EN LA BD

	public static void agregarEjercicio(Ejercicio ejercicio) {
		try {
			PreparedStatement statementusuario = con.prepareStatement(
					"INSERT INTO `ejercicio`( `nombre_ejercicio`, `musculo`, `dificultad`, `cant_repeticiones`, `cant_series`) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statementusuario.setString(1, ejercicio.getNombre_ejercicio());
			statementusuario.setString(2, ejercicio.getMusculo());
			statementusuario.setString(3, ejercicio.getDificultad());
			statementusuario.setInt(4, ejercicio.getCant_repeticiones());
			statementusuario.setInt(5, ejercicio.getCant_series());

			int filas = statementusuario.executeUpdate();
			if (filas > 0) {
				ResultSet rs = statementusuario.getGeneratedKeys();
				if (rs.next()) {
					int id_ejercicio = rs.getInt(1);
					ejercicio.setId_ejercicio(id_ejercicio);
					historial.add(" Ejercicio Agregado a la BD ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// AÑADIR SUBSCRIPCION EN LA BD

	public static void AgregarSubscripcion(Subscripcion subs) {
		try {
			PreparedStatement statementsubscripcion = con.prepareStatement(
					"INSERT INTO `subscripcion`( `nombresubs`, `monto`, `duracion`, `descripcion`) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statementsubscripcion.setString(1, subs.getNombresubs());
			statementsubscripcion.setInt(2, subs.getMonto());
			statementsubscripcion.setInt(3, subs.getDuracion());
			statementsubscripcion.setString(4, subs.getDescripcion());

			int filas = statementsubscripcion.executeUpdate();
			historial.add(" Subscripción Agregada a la BD ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}