package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Subscripcion;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SubscripcionPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input_nombre_subs;
	private JTextField input_monto_subscripcion;
	private JTextField input_duracion_subs;
	private JTextField input_descripcion;
	private JLabel text_error;

	public void run() {
		try {
			SubscripcionPanel frame = new SubscripcionPanel();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public SubscripcionPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 701);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// BOTON VOLVER

		JButton buttom_back = new JButton("Volver");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 11, 179, 48);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorMenu profesormenu = new ProfesorMenu();
				profesormenu.run();
				dispose();
			}
		});
		contentPane.add(buttom_back);

		// TITLE SUBSCRIPCION

		JLabel title_subscripcion = new JLabel("Crear nueva Subscripcion");
		title_subscripcion.setBounds(155, 102, 500, 78);
		contentPane.add(title_subscripcion);

		// TEXTO NOMBRE

		JLabel text_nombre_subs = new JLabel("Nombre de Subscripcion");
		text_nombre_subs.setBounds(65, 237, 285, 53);
		contentPane.add(text_nombre_subs);

		// INPUT NOMBRE

		input_nombre_subs = new JTextField();
		input_nombre_subs.setBounds(65, 292, 216, 30);
		contentPane.add(input_nombre_subs);
		input_nombre_subs.setColumns(10);

		// TEXTO PRECIO

		JLabel text_monto_subs = new JLabel("Precio de Subscripcion");
		text_monto_subs.setBounds(65, 333, 203, 33);
		contentPane.add(text_monto_subs);

		// INPUT PRECIO
		input_monto_subscripcion = new JTextField();
		input_monto_subscripcion.setBounds(65, 372, 216, 30);
		contentPane.add(input_monto_subscripcion);
		input_monto_subscripcion.setColumns(10);

		// TEXTO DURACION

		JLabel text_duracion = new JLabel("Duración de la Subscripción");
		text_duracion.setBounds(354, 237, 376, 53);
		contentPane.add(text_duracion);

		// INPUT DURACION

		input_duracion_subs = new JTextField();
		input_duracion_subs.setBounds(354, 292, 249, 30);
		contentPane.add(input_duracion_subs);
		input_duracion_subs.setColumns(10);

		// TEXTO DESCRIPCION

		JLabel text_descripcion_subs = new JLabel("Descripcion");
		text_descripcion_subs.setBounds(354, 337, 249, 24);
		contentPane.add(text_descripcion_subs);

		// INPUT DESCRIPCION

		input_descripcion = new JTextField();
		input_descripcion.setBounds(354, 372, 250, 30);
		contentPane.add(input_descripcion);
		input_descripcion.setColumns(10);

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(20, 432, 664, 63);
		contentPane.add(text_error);

		// BOTON CREAR

		JButton buttom_submit = new JButton("CREAR");
		buttom_submit.setForeground(new Color(0, 128, 64));
		buttom_submit.setBounds(210, 522, 216, 53);
		contentPane.add(buttom_submit);
		buttom_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = input_nombre_subs.getText().trim();
				String precio0 = input_monto_subscripcion.getText().trim();
				String duracion0 = input_duracion_subs.getText().trim();
				String descripcion = input_descripcion.getText().trim();
				if (nombre.isEmpty() || precio0.isEmpty() || duracion0.isEmpty() || descripcion.isEmpty()) {
					text_error.setText(" Rellene todos los campos con la información correspondiente ");
					return;
				}
				int precio;
				int duracion;
				try {
					precio = Integer.parseInt(precio0);
					duracion = Integer.parseInt(duracion0);
				} catch (NumberFormatException ex) {
					text_error.setText(" El Precio y Duración deben ser números enteros válidos ");
					return;
				}
				if (precio <= 0 || duracion <= 0) {
					text_error.setText(" El Precio y la Duración deben ser mayores a 0");
					return;
				}
				Subscripcion nuevasubs = new Subscripcion(nombre, precio, duracion, descripcion);
				Administrador.AgregarSubscripcion(nuevasubs);
				ProfesorMenu profesormenu = new ProfesorMenu();
				profesormenu.run();
				dispose();

			}
		});

		// BLOQUE PARA AÑADIR FUENTES

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 40f);
			title_subscripcion.setFont(bebasNeue);
			buttom_submit.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			text_nombre_subs.setFont(roboto);
			text_duracion.setFont(roboto);
			text_descripcion_subs.setFont(roboto);
			text_monto_subs.setFont(roboto);
			input_nombre_subs.setFont(roboto);
			input_monto_subscripcion.setFont(roboto);
			input_duracion_subs.setFont(roboto);
			input_descripcion.setFont(roboto);
			text_error.setFont(roboto);

		} catch (Exception e) {
			;
			e.printStackTrace();
			title_subscripcion.setFont(new Font("SansSerif", Font.BOLD, 24));
		}
	}
}
