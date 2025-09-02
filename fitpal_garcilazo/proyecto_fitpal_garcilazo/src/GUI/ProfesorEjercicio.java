package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.IllegalFormatCodePointException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Ejercicio;
import BLL.Profesor;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ProfesorEjercicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input_nombre_ejercicio;
	private JLabel text_error;

	public void run() {
		try {
			ProfesorEjercicio frame = new ProfesorEjercicio();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProfesorEjercicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 563);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// BOTON PARA VOLVER

		JButton buttom_back = new JButton("VOLVER");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 11, 156, 51);
		contentPane.add(buttom_back);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorMenu profesormenu = new ProfesorMenu();
				profesormenu.run();
				dispose();
			}
		});

		// TEXTO TITLE PROFESOR EJECICIO

		JLabel title_profesor_add = new JLabel("REGISTRO DE EJERCICIOS");
		title_profesor_add.setBounds(126, 67, 378, 59);
		contentPane.add(title_profesor_add);

		// TEXTO SUBTITLE PROFESOR EJERCICIO

		JLabel text_profesor = new JLabel("Menú de profesor para registrar un ejercicio nuevo");
		text_profesor.setForeground(new Color(128, 128, 128));
		text_profesor.setBounds(118, 116, 361, 52);
		contentPane.add(text_profesor);

		// TEXTO NOMBRE EJERCICIO

		JLabel text_nombre_ejercicio = new JLabel("Nombre del ejercicio");
		text_nombre_ejercicio.setBounds(10, 154, 299, 52);
		contentPane.add(text_nombre_ejercicio);

		// INPUT NOMBRE EJERCICIO

		input_nombre_ejercicio = new JTextField();
		input_nombre_ejercicio.setBounds(10, 200, 194, 33);
		contentPane.add(input_nombre_ejercicio);
		input_nombre_ejercicio.setColumns(10);

		// TEXTO MUSCULO

		JLabel text_musculo = new JLabel("Musculo que trabaja el ejercicio");
		text_musculo.setBounds(10, 261, 396, 23);
		contentPane.add(text_musculo);

		// TEXTO NIVEL DIFICULTAD

		JLabel text_nivel_dificultad = new JLabel("Seleccione el nivel de dificultad");
		text_nivel_dificultad.setBounds(303, 169, 291, 23);
		contentPane.add(text_nivel_dificultad);

		// OPCIONES DIFICULTAD

		JComboBox opciones_dificultad = new JComboBox();
		opciones_dificultad.setModel(new DefaultComboBoxModel(new String[] { "Principiante", "Intermedio", "Avanzado" }));
		opciones_dificultad.setBounds(342, 200, 194, 37);
		contentPane.add(opciones_dificultad);

		// TEXTO CANTIDAD DE REPETICIONES

		JLabel text_cant_repes = new JLabel("Seleccione la cantidad de repeticiones");
		text_cant_repes.setBounds(303, 261, 342, 23);
		contentPane.add(text_cant_repes);

		// OPCIONES CANTIDAD DE REPETICIONES

		JComboBox opciones_repeticiones = new JComboBox();
		opciones_repeticiones.setModel(new DefaultComboBoxModel(new String[] { "6", "8", "12", "16" }));
		opciones_repeticiones.setBounds(342, 295, 194, 33);
		contentPane.add(opciones_repeticiones);

		// TEXTO CANTIDAD DE SERIES

		JLabel text_series = new JLabel("Seleccione la cantidad de series");
		text_series.setBounds(304, 357, 324, 23);
		contentPane.add(text_series);

		// OPCIONES CANTIDAD DE SERIES

		JComboBox opciones_series = new JComboBox();
		opciones_series.setModel(new DefaultComboBoxModel(new String[] { "2", "4", "6", "8" }));
		opciones_series.setBounds(342, 391, 194, 35);
		contentPane.add(opciones_series);
		
		// OPCIONES MUSCULO
		
		JComboBox opciones_musculo = new JComboBox();
		opciones_musculo.setModel(new DefaultComboBoxModel(new String[] {"Pectoral", "Hombro", "Triceps", "Biceps", "Espalda Alta", "Espalda Baja", "Cuadriceps", "Antebrazo", "Abdominales", "Gemelos", "Isquiotibiales"}));
		opciones_musculo.setBounds(10, 307, 194, 33);
		contentPane.add(opciones_musculo);
		
		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(10, 399, 413, 63);
		contentPane.add(text_error);

		// BOTON PARA REGISTRAR EJERCICIO

		JButton buttom_submit = new JButton("Registrar");
		buttom_submit.setForeground(new Color(0, 128, 64));
		buttom_submit.setBounds(126, 462, 342, 51);
		buttom_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cant_repes0 = opciones_repeticiones.getSelectedItem().toString();
				int cant_repes = Integer.parseInt(cant_repes0);
				String cant_series0 = opciones_series.getSelectedItem().toString();
				int cant_series = Integer.parseInt(cant_series0);
				String dificultad = opciones_dificultad.getSelectedItem().toString();
				String musculo = opciones_musculo.getSelectedItem().toString();
				Ejercicio ejercicionuevo = new Ejercicio(0, "", "", dificultad, cant_repes, cant_series);
				if (!input_nombre_ejercicio.getText().isEmpty()) {
					ejercicionuevo.setNombre_ejercicio(input_nombre_ejercicio.getText());
				} else {
					text_error.setText("El campo Nombre está vacío");
				}
				if (!musculo.isEmpty()) {
					ejercicionuevo.setMusculo(musculo);
				} else {
					text_error.setText("El campo Músculo está vacío");
				}
				if (!input_nombre_ejercicio.getText().isEmpty() && !musculo.isEmpty()) {
					Administrador.agregarEjercicio(ejercicionuevo);
				}

			}
		});
		contentPane.add(buttom_submit);

		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_profesor_add.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);
			buttom_submit.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 16f);
			text_profesor.setFont(roboto);
			text_nombre_ejercicio.setFont(roboto);
			input_nombre_ejercicio.setFont(roboto);
			text_musculo.setFont(roboto);
			text_nivel_dificultad.setFont(roboto);
			opciones_dificultad.setFont(roboto);
			text_cant_repes.setFont(roboto);
			opciones_repeticiones.setFont(roboto);
			text_series.setFont(roboto);
			opciones_series.setFont(roboto);
			opciones_musculo.setFont(roboto);
	

		} catch (Exception e) {
			e.printStackTrace();
			title_profesor_add.setFont(new Font("SansSerif", Font.BOLD, 24));
		}
	}
}
