package GUI;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.Cliente;
import BLL.Ejercicio;

import java.awt.Color;
import javax.swing.JTextArea;

public class RegistrarEntrenamiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Ejercicio ejercicioSeleccionado;
	private String ejercicioSeleccionado2 = "";
	public JTextArea text_rutina;
	private String rutina;
	private Cliente cliente;
	
	public String getRutina() {
		return rutina;
	}

	public void setRutina(String rutina) {
		this.rutina = rutina;
	}

	public void run(Cliente cliente) {
		try {
			RegistrarEntrenamiento frame = new RegistrarEntrenamiento(cliente);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistrarEntrenamiento(Cliente cliente) {
		this.cliente = cliente;
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
				ClienteMenu clientemenu = new ClienteMenu(cliente);
				clientemenu.run(cliente);
				dispose();
			}
		});
		contentPane.add(buttom_back);

		// MODELO DE LA TABLA

		model = new DefaultTableModel(new String[] { "id_ejercicio", "nombre_ejercicio", "musculo", "dificultad",
				"cant_repeticiones", "cant_series" }, 0);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(271, 11, 403, 362);
		contentPane.add(scrollPane);

		JTableHeader header = table.getTableHeader();

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setForeground(new Color(128, 0, 0));
		text_error.setBounds(96, 390, 664, 63);
		contentPane.add(text_error);

		// TEXTO DE INFO

		JLabel text_info = new JLabel("");
		text_info.setBounds(20, 475, 664, 63);
		contentPane.add(text_info);

		// TABLE EJERCICIOS

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					ejercicioSeleccionado = new Ejercicio((int) model.getValueAt(row, 0),
							(String) model.getValueAt(row, 1), (String) model.getValueAt(row, 2),
							(String) model.getValueAt(row, 3), (int) model.getValueAt(row, 4),
							(int) model.getValueAt(row, 5));
				}
			}
		});

		cargarTabla();
		
		// TEXT AREA RUTINA
		
		text_rutina = new JTextArea();
		text_rutina.setEditable(false);
		text_rutina.setLineWrap(true);
		text_rutina.setWrapStyleWord(true);
	

		JScrollPane scrollTextRutina = new JScrollPane(text_rutina);
		scrollTextRutina.setBounds(20, 60, 250, 300);
		contentPane.add(scrollTextRutina);

		JLabel title_rutina = new JLabel("Rutina");
		title_rutina.setFont(new Font("Roboto", Font.BOLD, 18));
		title_rutina.setBounds(89, 25, 100, 25);
		contentPane.add(title_rutina);

		// BOTON PARA AÑADIR EJERCICIO
		
		
		JButton buttom_aniadir = new JButton("Añadir Ejercicio");
		buttom_aniadir.setForeground(new Color(0, 128, 64));
		buttom_aniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ejercicioSeleccionado != null) {
					String rutina = text_rutina.getText(); 
					if (!rutina.contains(ejercicioSeleccionado.getNombre_ejercicio())) {
						ejercicioSeleccionado2 += "• " + ejercicioSeleccionado.getNombre_ejercicio() + " | Series: "
								+ ejercicioSeleccionado.getCant_series() + " | Reps: "
								+ ejercicioSeleccionado.getCant_repeticiones() + "\n";
						text_rutina.setText(ejercicioSeleccionado2);
						text_error.setText("");
						cliente.setRutina(text_rutina.getText());
					} else {
						text_error.setText(" El ejercicio seleccionado ya está en la rutina");
					}
				} else {
					text_error.setText(" Debes seleccionar un ejercicio primero o un ejercicio nuevo");
				}
			}
		});
		buttom_aniadir.setBackground(new Color(255, 255, 255));
		buttom_aniadir.setBounds(288, 566, 360, 84);
		contentPane.add(buttom_aniadir);

		JButton buttom_listo = new JButton("Listo");
		buttom_listo.setForeground(new Color(0, 128, 64));
		buttom_listo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteMenu clientemenu = new ClienteMenu(cliente);
				clientemenu.run(cliente);
			}
		});
		buttom_listo.setBackground(new Color(255, 255, 255));
		buttom_listo.setBounds(288, 566, 360, 84);
		contentPane.add(buttom_listo);
		// FUENTE PERSONALIZADA

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			buttom_aniadir.setFont(bebasNeue);
			buttom_listo.setFont(bebasNeue);
			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);

			InputStream is3 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto2 = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 15f);
			header.setFont(roboto2);
			table.setFont(roboto2);
			table.setRowHeight(25);
			text_error.setFont(roboto2);
			text_rutina.setFont(roboto2);
			text_info.setFont(roboto2);
			text_rutina.setFont(roboto2);
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
