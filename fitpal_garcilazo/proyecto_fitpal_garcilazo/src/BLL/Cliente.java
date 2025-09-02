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
import repository.Encriptador;

public class Cliente extends Usuario implements Encriptador {
	private String tiposubs;
	private String subsestado;
	private int id_cliente;
	private String rutina = "";

	// GETTERS Y SETTERS

	public Cliente(String nombre, String apellido, String email, String password, int dni, String tiposubs,
			String subsestado, String rutina) {
		super(nombre, apellido, email, password, dni, tiposubs);
		this.tiposubs = tiposubs;
		this.subsestado = subsestado;
		this.rutina = rutina;
	}

	public Cliente(int id_cliente, String nombre, String apellido, String email, String password, int dni,
			String tiposubs, String subsestado, String rutina) {
		super(id_cliente, nombre, apellido, email, password, dni, tiposubs);
		this.id_cliente = id_cliente;
		this.tiposubs = tiposubs;
		this.subsestado = subsestado;
		this.rutina = rutina;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getTiposubs() {
		return tiposubs;
	}

	public void setTiposubs(String tiposubs) {
		this.tiposubs = tiposubs;
	}

	public String getSubsestado() {
		return subsestado;
	}

	public void setSubsestado(String subsestado) {
		this.subsestado = subsestado;
	}

	public String getRutina() {
		return rutina;
	}

	public void setRutina(String rutina) {
		this.rutina = rutina;
	}

	// CONEXION

	static Connection con = Conexion.getInstance().getConnection();

	// METODO PARA MOSTRAR CLIENTES

	public static LinkedList<Cliente> mostrarClientes() {
		LinkedList<Cliente> clientes = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cliente");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_cliente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int dni = rs.getInt("dni");
				String tiposubs = rs.getString("tiposubs");
				String subsestado = rs.getString("subsestado");

				clientes.add(new Cliente(id, nombre, apellido, email, password, dni, tiposubs, subsestado, ""));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	// METODO PARA CANCELAR LA SUBSCRIPCION

	public static void CancelSubs(Cliente cliente) {
		try {
			PreparedStatement statementcliente = con
					.prepareStatement("UPDATE `cliente` SET `subsestado` = ? WHERE `id_cliente` = ?");
			statementcliente.setString(1, "Cancelado");
			statementcliente.setInt(2, cliente.getId_cliente());

			statementcliente.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// METODO PARA RENOVAR LA SUBSCRIPCION

	public static void UpdateSubs(Cliente cliente) {
		try {
			PreparedStatement statementusuario = con
					.prepareStatement("UPDATE `cliente` SET  `subsestado` = ? WHERE `id_cliente` = ?");
			statementusuario.setString(1, "Vigente");
			statementusuario.setInt(2, cliente.getId_cliente());

			statementusuario.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
