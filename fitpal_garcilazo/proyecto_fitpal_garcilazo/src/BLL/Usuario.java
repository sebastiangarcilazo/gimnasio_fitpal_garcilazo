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
import DLL.ControllerUsuario;
import GUI.MainPanel;
import repository.Encriptador;

public class Usuario implements Encriptador {
	protected int id;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected String password;
	protected int dni;
	protected String tipousuario;
	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();

	public Usuario(int id, String nombre, String apellido, String email, String password, int dni, String tipousuario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.dni = dni;
		this.tipousuario = tipousuario;
	}

	public Usuario(String nombre, String apellido, String email, String password, int dni, String tipousuario) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.dni = dni;
		this.tipousuario = tipousuario;
	}
	// GETTERS Y SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}

	public static LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(LinkedList<Usuario> usuarios) {
		Usuario.usuarios = usuarios;
	}
	
	// VALIDACION DE LOS DATOS DE REGISTRO DE USUARIO
	
	public static String RegistrarUsuarioValidacion(Usuario usuario) {
		String errores = "";
		if (!usuario.getEmail().contains("@")) {
			errores = errores + " - El Email debe contener @-\n";
		}
		if (!usuario.getEmail().contains("@gmail.com")) {
			errores = errores + " - La dirección Email debe ser válida-\n";
		}
		if (usuario.getNombre().isEmpty()) {
			errores = errores + " - El Nombre está vacio-\n";
		}
		if (usuario.getApellido().isEmpty()) {
			errores = errores + " - El Apellido está vacio-\n";
		}
		if (usuario.getEmail().isEmpty()) {
			errores = errores + " - El Email está vacio-\n";
		}
		if (usuario.getPassword().isEmpty()) {
			errores = errores + " - La Contraseña está vacia-\n";
		}
		if (usuario.getPassword().equals(usuario.getEmail())) {
			errores = errores + " - La Contraseña y el Email no pueden ser idénticos-\n";
		}
		if (usuario.getPassword().equals(usuario.getNombre())) {
			errores = errores + " - La Contraseña no puede ser igual que el Nombre-\n";
		}
		if (usuario.getPassword().equals(usuario.getApellido())) {
			errores = errores + " - La Contraseña no puede ser igual que el Apellido-\n";
		}
		if (usuario.getTipousuario().isEmpty()) {
			errores = errores + " - El Tipo de Usuario está vacio-\n";
		}
		if (usuario.getDni() <= 0) {
			errores = errores + " - El Dni está vacio-\n";
		}
		if (usuario.getDni() < 1000000 || usuario.getDni() > 99999999) {
			errores = errores + "- DNI inválido (debe tener entre 7 y 8 dígitos)-\n";
		}
		if (usuario.ValidarEmail()) {
			errores = errores + "- El email ya está registrado-\n";
		}
		if (errores.isEmpty()) {
			return ControllerUsuario.agregarUsuario(usuario);
		}
		return errores;

	}

	// VALIDA QUE EL MAIL NO ESTÉ REGISTRADO EN LA BASE DE DATOS

	public boolean ValidarEmail() {
		Usuario usuario = null;
		LinkedList<Usuario> existentes = ControllerUsuario.mostrarUsuarios();
		for (Usuario existente : existentes) {
			if (existente.getEmail().equalsIgnoreCase(this.getEmail())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nombre = " + nombre + ", apellido = " + apellido + ", email = " + email + ", password = "
				+ password + ", dni = " + dni + ", tipousuario = " + tipousuario;
	}

}