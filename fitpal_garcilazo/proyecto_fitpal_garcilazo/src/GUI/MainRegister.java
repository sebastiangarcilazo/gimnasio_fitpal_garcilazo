package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.security.cert.PKIXRevocationChecker.Option;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.OptionPaneUI;

import BLL.Administrador;
import BLL.Cliente;
import BLL.Profesor;
import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class MainRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input_email;
	private JTextField input_dni;
	private JTextField input_nombre;
	private JTextField input_apellido;
	private JPasswordField input_password;

	public void run() {
		try {
			MainRegister frame = new MainRegister();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 128, 192));
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TITLE

		JLabel title = new JLabel("Crea una cuenta en FITPAL");
		title.setBounds(141, 94, 464, 49);
		contentPane.add(title);

		// BOTON VOLVER

		JButton buttom_back = new JButton("Volver");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 11, 151, 49);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel mainpanel = new MainPanel();
				mainpanel.run();
				dispose();
			}
		});
		contentPane.add(buttom_back);

		// TEXTO EMAIL

		JLabel text_email = new JLabel("Email");
		text_email.setBounds(160, 325, 129, 21);
		contentPane.add(text_email);

		// INPUT EMAIL

		input_email = new JTextField();
		input_email.setBounds(160, 362, 191, 36);
		contentPane.add(input_email);
		input_email.setColumns(10);

		// TEXTO DNI

		JLabel text_dni = new JLabel("DNI");
		text_dni.setBounds(160, 244, 46, 14);
		contentPane.add(text_dni);

		// INPUT DNI

		input_dni = new JTextField();
		input_dni.setBounds(160, 278, 191, 36);
		contentPane.add(input_dni);
		input_dni.setColumns(10);

		// TEXTO NOMBRE

		JLabel text_nombre = new JLabel("Nombre");
		text_nombre.setBounds(160, 158, 185, 14);
		contentPane.add(text_nombre);

		// INPUT NOMBRE

		input_nombre = new JTextField();
		input_nombre.setBounds(160, 183, 185, 36);
		contentPane.add(input_nombre);
		input_nombre.setColumns(10);

		// TEXTO APELLIDO

		JLabel text_apellido = new JLabel("Apellido");
		text_apellido.setBounds(375, 154, 176, 23);
		contentPane.add(text_apellido);

		// INPUT APELLIDO

		input_apellido = new JTextField();
		input_apellido.setBounds(375, 185, 176, 32);
		contentPane.add(input_apellido);
		input_apellido.setColumns(10);

		// TEXTO PASSWORD

		JLabel text_password = new JLabel("Contraseña");
		text_password.setBounds(160, 417, 151, 23);
		contentPane.add(text_password);

		// INPUT PASSWORD

		input_password = new JPasswordField();
		input_password.setBounds(160, 451, 191, 36);
		contentPane.add(input_password);

		// TEXT USUARIO

		JLabel text_tipousuario = new JLabel("Tipo de usuario");
		text_tipousuario.setFont(new Font("Roboto", Font.PLAIN, 20));
		text_tipousuario.setBounds(375, 247, 191, 20);
		contentPane.add(text_tipousuario);

		// INPUT TIPO USUARIO

		JComboBox opciones_usuario = new JComboBox();
		opciones_usuario.setModel(new DefaultComboBoxModel(new String[] { "Cliente", "Profesor" }));
		opciones_usuario.setBounds(375, 278, 113, 36);
		contentPane.add(opciones_usuario);

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(10, 587, 664, 63);
		contentPane.add(text_error);

		// BOTON REGISTRO

		JButton buttom_register = new JButton("Registrarse");
		buttom_register.setForeground(new Color(0, 128, 192));
		buttom_register.setBounds(219, 509, 240, 67);
		buttom_register.setBackground(new Color(255, 255, 255));
		buttom_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// CONVERTIR OPCION DE USUARIO A STRING

				String tipousuario = opciones_usuario.getSelectedItem().toString();

				// CONVERTIR STRING DNI A INT PARA GUARDARLO

				int dni = Integer.parseInt((input_dni.getText().isEmpty()) ? "0" : input_dni.getText());

				// CREO EL OBJETO USUARIO

				Usuario usuario = new Usuario(input_nombre.getText(), input_apellido.getText(), input_email.getText(),
						input_password.getText(), dni, tipousuario);

				// VALIDADOR DATOS DE USUARIO

				text_error.setText(Usuario.RegistrarUsuarioValidacion(usuario));

				if (usuario.getId() != 0) {

					// AGREGO CLIENTE A LA BD

					if (usuario.getTipousuario().equals("Cliente")) {
						Cliente cliente = new Cliente(0,input_nombre.getText(), input_apellido.getText(),
								input_email.getText(), input_password.getText(), dni, tipousuario, "", "");
						cliente.setSubsestado("Vigente");
						Administrador.agregarCliente(cliente, usuario);
						MainPanel mainpanel = new MainPanel();
						mainpanel.run();
						dispose();
					}

					// AGREGO PROFESOR A LA BD

					if (usuario.getTipousuario().equals("Profesor")) {
						Profesor profesor = new Profesor(input_nombre.getText(), input_apellido.getText(),
								input_email.getText(), input_password.getText(), dni, tipousuario);
						Administrador.agregarProfesor(profesor, usuario);;
						MainPanel mainpanel = new MainPanel();
						mainpanel.run();
						dispose();
					}
				} 
			}
		});
		contentPane.add(buttom_register);

		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			buttom_register.setFont(bebasNeue);
			title.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			input_nombre.setFont(roboto);
			input_apellido.setFont(roboto);
			input_email.setFont(roboto);
			input_dni.setFont(roboto);
			text_nombre.setFont(roboto);
			text_apellido.setFont(roboto);
			text_email.setFont(roboto);
			text_dni.setFont(roboto);
			text_password.setFont(roboto);
			text_tipousuario.setFont(roboto);
			opciones_usuario.setFont(roboto);

			InputStream is3 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto2 = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 15f);
			text_error.setFont(roboto2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
