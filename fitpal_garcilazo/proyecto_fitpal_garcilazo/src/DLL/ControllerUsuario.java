package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import BLL.Administrador;
import BLL.Cliente;
import BLL.Profesor;
import BLL.Usuario;
import repository.Encriptador;

public class ControllerUsuario {

	// CONEXION

	protected static Connection con = Conexion.getInstance().getConnection();

	// AGREGA EL USUARIO A LA BASE DE DATOS

	public static String agregarUsuario(Usuario usuario) {
		try {
			PreparedStatement statementusuario = con.prepareStatement(
					"INSERT INTO `usuario`( `nombre`, `apellido`, `email`, `password`, `dni`, `tipousuario`) VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			statementusuario.setString(1, usuario.getNombre());
			statementusuario.setString(2, usuario.getApellido());
			statementusuario.setString(3, usuario.getEmail());
			statementusuario.setString(4, Encriptador.encriptar(usuario.getPassword()));
			statementusuario.setInt(5, usuario.getDni());
			statementusuario.setString(6, usuario.getTipousuario());

			int filas = statementusuario.executeUpdate();
			if (filas > 0) {
				ResultSet rs = statementusuario.getGeneratedKeys();
				if (rs.next()) {
					int id_usuario = rs.getInt(1);
					usuario.setId(id_usuario);
				}
				return "Usuario email: " + usuario.getEmail() + " Registrado con éxito";
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			return "Mail existente";
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Mail existente");
		}
		return "No se agrego";

	}
	
	// METODO PARA MOSTRAR USUARIOS

	public static LinkedList<Usuario> mostrarUsuarios() {
		LinkedList<Usuario> usuarios = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int dni = rs.getInt("dni");
				String tipousuario = rs.getString("tipousuario");

				usuarios.add(new Usuario(id, nombre, apellido, email, password, dni, tipousuario));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}


	// METODO PARA INICIAR SESION

	public static Usuario IniciarSesion(String email, String passwordIngresado) {
		Usuario usuario = null;
		try {
			String passwordEncriptado = Encriptador.encriptar(passwordIngresado);

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? AND password = ?");
			stmt.setString(1, email);
			stmt.setString(2, passwordEncriptado);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int dni = rs.getInt("dni");
				String tipousuario = rs.getString("tipousuario");

				usuario = new Usuario(id, nombre, apellido, email, passwordIngresado, dni, tipousuario);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
