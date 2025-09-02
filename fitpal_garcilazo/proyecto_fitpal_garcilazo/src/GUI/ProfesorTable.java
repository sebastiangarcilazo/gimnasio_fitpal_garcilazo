package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.Ejercicio;
import BLL.Profesor;

import java.awt.Color;

public class ProfesorTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Ejercicio ejercicioSeleccionado;
	private JTextField inpFiltro;
	private JLabel lblSeleccionado;
	private JLabel text_error;

	public void run() {
		try {
			ProfesorTable frame = new ProfesorTable();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProfesorTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// BOTON VOLVER

		JButton buttom_back = new JButton("Volver");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 602, 179, 48);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorMenu profesormenu = new ProfesorMenu();
				profesormenu.run();
				dispose();
			}
		});
		contentPane.add(buttom_back);

		// TEXTO EJERCICIO SELECCIONADO

		JLabel text_ejercicio_seleccionado = new JLabel("Ejercicio seleccionado:");
		text_ejercicio_seleccionado.setForeground(new Color(128, 128, 128));
		text_ejercicio_seleccionado.setBounds(9, 384, 180, 33);
		contentPane.add(text_ejercicio_seleccionado);

		lblSeleccionado = new JLabel("");
		lblSeleccionado.setBounds(10, 414, 653, 61);
		contentPane.add(lblSeleccionado);

		// MODELO DE LA TABLA

		model = new DefaultTableModel(new String[] { "id_ejercicio", "nombre_ejercicio", "musculo", "dificultad",
				"cant_repeticiones", "cant_series" }, 0);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 664, 362);
		contentPane.add(scrollPane);

		JTableHeader header = table.getTableHeader();

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(89, 547, 664, 63);
		contentPane.add(text_error);

		// TABLE EJERCICIOS

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					ejercicioSeleccionado = new Ejercicio((int) model.getValueAt(row, 0),
							(String) model.getValueAt(row, 1), (String) model.getValueAt(row, 2),
							(String) model.getValueAt(row, 3), (int) model.getValueAt(row, 4),
							(int) model.getValueAt(row, 5));
					lblSeleccionado.setText(ejercicioSeleccionado.toString());
				}
			}
		});

		cargarTabla();

		// BOTON PARA EDITAR EJERCICIO

		JButton buttom_editar = new JButton("Editar");
		buttom_editar.setForeground(new Color(0, 128, 192));
		buttom_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ejercicioSeleccionado != null && !lblSeleccionado.getText().isEmpty()) {
					String ejercicioseleccionado = ejercicioSeleccionado.toString();
					UpdateEjercicios updateejercicios = new UpdateEjercicios(ejercicioSeleccionado);
					updateejercicios.run(ejercicioSeleccionado);
					dispose();
				} else {
					text_error.setText(" Para editar debes seleccionar un ejercicio primero ");
				}
			}
		});
		buttom_editar.setBackground(new Color(255, 255, 255));
		buttom_editar.setBounds(371, 468, 235, 84);
		contentPane.add(buttom_editar);

		// BOTON ELIMINAR EJERCICIO

		JButton buttom_eliminar = new JButton("Eliminar");
		buttom_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ejercicioSeleccionado != null && !lblSeleccionado.getText().isEmpty()) {
					Profesor.EliminarEjercicio(ejercicioSeleccionado);
					cargarTabla();
				} else {
					text_error.setText(" Para eliminar debes seleccionar un ejercicio primero ");
				}

			}
		});
		buttom_eliminar.setForeground(new Color(128, 64, 64));
		buttom_eliminar.setBounds(90, 468, 235, 84);
		contentPane.add(buttom_eliminar);

		// FUENTE PERSONALIZADA

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			buttom_editar.setFont(bebasNeue);
			buttom_eliminar.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);

			InputStream is3 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto2 = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 15f);
			header.setFont(roboto2);
			table.setFont(roboto2);
			table.setRowHeight(25);
			lblSeleccionado.setFont(roboto2);
			text_ejercicio_seleccionado.setFont(roboto2);
			text_error.setFont(roboto2);
			InputStream is4 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue2 = Font.createFont(Font.TRUETYPE_FONT, is4).deriveFont(Font.PLAIN, 20f);
			buttom_back.setFont(bebasNeue2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// FUNCION PARA CARGAR LA TABLA

	public void cargarTabla() {
		model.setRowCount(0);
		Ejercicio ejercicio = new Ejercicio(0, "", "", "", 0, 0);
		LinkedList<Ejercicio> ejercicios = ejercicio.mostrarEjercicios();
		for (Ejercicio e : ejercicios) {
			model.addRow(new Object[] { e.getId_ejercicio(), e.getNombre_ejercicio(), e.getMusculo(), e.getDificultad(),
					e.getCant_repeticiones(), e.getCant_series() });
		}
	}
}
