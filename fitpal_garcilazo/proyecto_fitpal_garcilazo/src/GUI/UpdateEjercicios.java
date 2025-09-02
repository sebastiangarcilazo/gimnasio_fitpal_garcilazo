package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Ejercicio;
import BLL.Profesor;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class UpdateEjercicios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Ejercicio ejercicio;
	private JTextField input_newnombre;
	private JTextField input_newmusculo;

	public void run(Ejercicio ejercicio) {
		try {
			UpdateEjercicios frame = new UpdateEjercicios(ejercicio);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public UpdateEjercicios(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
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
		buttom_back.setBounds(10, 2, 146, 57);
		contentPane.add(buttom_back);
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorTable profesortable = new ProfesorTable();
				profesortable.run();
				dispose();
			}
		});

		// TITLE EDITAR EJERCICIO

		JLabel title_updatejercicio = new JLabel("EDITAR EJERCICIO");
		title_updatejercicio.setBounds(207, 53, 253, 64);
		contentPane.add(title_updatejercicio);

		// TEXTO NUEVO NOMBRE

		JLabel text_newnombre = new JLabel("Nuevo Nombre ");
		text_newnombre.setBounds(69, 209, 193, 18);
		contentPane.add(text_newnombre);

		// INPUT NUEVO NOMBRE

		input_newnombre = new JTextField();
		input_newnombre.setBounds(67, 238, 195, 33);
		contentPane.add(input_newnombre);
		input_newnombre.setColumns(10);

		// TEXTO NUEVO MUSCULO

		JLabel text_newmusculo = new JLabel("Nuevo Músculo");
		text_newmusculo.setBounds(69, 282, 146, 40);
		contentPane.add(text_newmusculo);

		// INPUT NUEVO MUSCULO

		input_newmusculo = new JTextField();
		input_newmusculo.setBounds(67, 333, 195, 33);
		contentPane.add(input_newmusculo);
		input_newmusculo.setColumns(10);

		// TEXTO NUEVA DIFICULTAD

		JLabel text_newdificultad = new JLabel("Nueva Dificultad");
		text_newdificultad.setBounds(69, 384, 175, 34);
		contentPane.add(text_newdificultad);

		// OPCIONES NUEVA DIFICULTAD

		JComboBox opciones_newdificultad = new JComboBox();
		opciones_newdificultad
				.setModel(new DefaultComboBoxModel(new String[] { "Principiante", "Intermedio", "Avanzado" }));
		opciones_newdificultad.setBounds(69, 429, 193, 48);
		contentPane.add(opciones_newdificultad);

		// TEXTO NUEVA CANTIDAD DE SERIES

		JLabel text_newseries = new JLabel("Nueva cantidad de Series");
		text_newseries.setBounds(375, 278, 309, 48);
		contentPane.add(text_newseries);

		// OPCIONES NUEVA CANTIDAD DE SERIES

		JComboBox opciones_newseries = new JComboBox();
		opciones_newseries.setModel(new DefaultComboBoxModel(new String[] { "2", "4", "6", "8", "12" }));
		opciones_newseries.setBounds(375, 333, 146, 33);
		contentPane.add(opciones_newseries);

		// TEXTO NUEVA CANTIDAD DE REPETICIONES

		JLabel text_newrepes = new JLabel("Nueva cantidad de Repeticiones");
		text_newrepes.setBounds(375, 195, 298, 47);
		contentPane.add(text_newrepes);

		// OPCIONES NUEVA CANTIDAD DE REPETICIONES

		JComboBox opciones_newrepes = new JComboBox();
		opciones_newrepes.setModel(new DefaultComboBoxModel(new String[] { "4", "8", "12", "18" }));
		opciones_newrepes.setBounds(375, 238, 139, 33);
		contentPane.add(opciones_newrepes);

		// TEXTO USUARIO SELECCIONADO

		JLabel text_ejercicio_seleccionado = new JLabel("Ejercicio seleccionado:");
		text_ejercicio_seleccionado.setForeground(new Color(0, 0, 0));
		text_ejercicio_seleccionado.setBounds(35, 115, 674, 57);
		contentPane.add(text_ejercicio_seleccionado);
		text_ejercicio_seleccionado.setText(" Nombre: " + ejercicio.getNombre_ejercicio() + " Músculo: "
				+ ejercicio.getMusculo() + " Dificultad: " + ejercicio.getDificultad() + " Cantidad de Series: "
				+ ejercicio.getCant_series() + " Cantidad de repeticiones: " + ejercicio.getCant_repeticiones());

		// BOTON PARA ACTUALIZAR EJERCICIO

		JButton buttom_updateejercicio = new JButton("ACTUALIZAR EJERCICIO");
		buttom_updateejercicio.setForeground(new Color(0, 128, 64));
		buttom_updateejercicio.setBounds(133, 583, 432, 67);
		contentPane.add(buttom_updateejercicio);
		buttom_updateejercicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cant_repes0 = opciones_newrepes.getSelectedItem().toString();
				int cant_repes = Integer.parseInt(cant_repes0);
				String cant_series0 = opciones_newseries.getSelectedItem().toString();
				int cant_series = Integer.parseInt(cant_series0);
				String newdificultad = opciones_newdificultad.getSelectedItem().toString();
				if (!input_newnombre.getText().isEmpty()) {
					ejercicio.setNombre_ejercicio(input_newnombre.getText());
				}
				if (!input_newmusculo.getText().isEmpty()) {
					ejercicio.setMusculo(input_newmusculo.getText());
				}
				ejercicio.setDificultad(newdificultad);
				ejercicio.setCant_series(cant_series);
				ejercicio.setCant_repeticiones(cant_repes);
				Profesor.UpdateEjercicio(ejercicio);
				ProfesorTable profesortable = new ProfesorTable();
				profesortable.run();
				dispose();
			}
		});

		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_updatejercicio.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 12f);
			text_ejercicio_seleccionado.setFont(roboto);

			InputStream is3 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto2 = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 20f);
			text_newnombre.setFont(roboto2);
			text_newmusculo.setFont(roboto2);
			text_newdificultad.setFont(roboto2);
			text_newrepes.setFont(roboto2);
			text_newseries.setFont(roboto2);
			input_newmusculo.setFont(roboto2);
			input_newnombre.setFont(roboto2);

			InputStream is4 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue2 = Font.createFont(Font.TRUETYPE_FONT, is4).deriveFont(Font.PLAIN, 20f);
			buttom_back.setFont(bebasNeue2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
