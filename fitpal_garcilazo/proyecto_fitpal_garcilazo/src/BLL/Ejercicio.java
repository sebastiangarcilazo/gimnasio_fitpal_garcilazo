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

public class Ejercicio {
	private int id_ejercicio;
	private String nombre_ejercicio;
	private String musculo;
	private String dificultad;
	private int cant_repeticiones;
	private int cant_series;

	public Ejercicio(int id_ejercicio, String nombre_ejercicio, String musculo, String dificultad,
			int cant_repeticiones, int cant_series) {
		super();
		this.id_ejercicio = id_ejercicio;
		this.nombre_ejercicio = nombre_ejercicio;
		this.musculo = musculo;
		this.dificultad = dificultad;
		this.cant_repeticiones = cant_repeticiones;
		this.cant_series = cant_series;
	}

	public int getId_ejercicio() {
		return id_ejercicio;
	}

	public void setId_ejercicio(int id_ejercicio) {
		this.id_ejercicio = id_ejercicio;
	}

	public String getNombre_ejercicio() {
		return nombre_ejercicio;
	}

	public void setNombre_ejercicio(String nombre_ejercicio) {
		this.nombre_ejercicio = nombre_ejercicio;
	}

	public String getMusculo() {
		return musculo;
	}

	public void setMusculo(String musculo) {
		this.musculo = musculo;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public int getCant_repeticiones() {
		return cant_repeticiones;
	}

	public void setCant_repeticiones(int cant_repeticiones) {
		this.cant_repeticiones = cant_repeticiones;
	}

	public int getCant_series() {
		return cant_series;
	}

	public void setCant_series(int cant_series) {
		this.cant_series = cant_series;
	}

	// CONEXION

	static Connection con = Conexion.getInstance().getConnection();

	// METODO PARA MOSTRAR EJERCICIOS

	public static LinkedList<Ejercicio> mostrarEjercicios() {
		LinkedList<Ejercicio> ejercicios = new LinkedList<>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ejercicio");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_ejercicio");
				String nombre_ejercicio = rs.getString("nombre_ejercicio");
				String musculo = rs.getString("musculo");
				String dificultad = rs.getString("dificultad");
				int cant_repeticiones = rs.getInt("cant_repeticiones");
				int cant_series = rs.getInt("cant_series");

				ejercicios
						.add(new Ejercicio(id, nombre_ejercicio, musculo, dificultad, cant_repeticiones, cant_series));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ejercicios;
	}

	@Override
	public String toString() {
		return "ID: " + id_ejercicio + ", Nombre: " + nombre_ejercicio + ", Músculo: " + musculo + ", Dificultad: "
				+ dificultad + ", Repeticiones: " + cant_repeticiones + ", Series: " + cant_series;
	}
}
