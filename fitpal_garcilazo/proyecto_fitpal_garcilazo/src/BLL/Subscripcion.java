package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import DLL.Conexion;

public class Subscripcion {
	private String nombresubs;
	private int monto;
	private int duracion;
	private String descripcion;

	public String getNombresubs() {
		return nombresubs;
	}

	public void setNombresubs(String nombresubs) {
		this.nombresubs = nombresubs;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Subscripcion(String nombresubs, int monto, int duracion, String descripcion) {
		super();
		this.nombresubs = nombresubs;
		this.monto = monto;
		this.duracion = duracion;
		this.descripcion = descripcion;
	}

	// CONEXION

	static Connection con = Conexion.getInstance().getConnection();

}
