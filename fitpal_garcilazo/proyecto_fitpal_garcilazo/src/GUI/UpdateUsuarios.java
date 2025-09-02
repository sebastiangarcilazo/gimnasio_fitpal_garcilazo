package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Usuario;
import repository.UsuarioRepository;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UpdateUsuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input_newnombre;
	private JTextField input_newapellido;
	private JTextField input_newemail;
	private JTextField input_newdni;
	private JTextField input_newcontraseña;
	private Usuario usuario;
	private JLabel text_seleccionado;
	private JLabel text_error;

	public void run(Usuario usuario) {
		try {
			UpdateUsuarios frame = new UpdateUsuarios(usuario);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UpdateUsuarios(Usuario usuario) {
		this.usuario = usuario;
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
		buttom_back.setBounds(10, 11, 146, 57);
		contentPane.add(buttom_back);
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenu adminmenu = new AdminMenu();
				adminmenu.run();
				dispose();
			}
		});

		// TITLE EDITAR USUARIO

		JLabel title_updateuser = new JLabel("EDITAR USUARIO");
		title_updateuser.setBounds(225, 30, 316, 57);
		contentPane.add(title_updateuser);

		// TEXTO NUEVO NOMBRE

		JLabel text_new_nombre = new JLabel("Nuevo Nombre");
		text_new_nombre.setBounds(38, 262, 194, 20);
		contentPane.add(text_new_nombre);

		// INPUT NUEVO NOMBRE

		input_newnombre = new JTextField();
		input_newnombre.setBounds(38, 293, 194, 30);
		contentPane.add(input_newnombre);
		input_newnombre.setColumns(10);

		// TEXTO NUEVO APELLIDO

		JLabel text_newapellido = new JLabel("Nuevo Apellido");
		text_newapellido.setBounds(38, 334, 194, 20);
		contentPane.add(text_newapellido);

		// INPUT NUEVO APELLIDO

		input_newapellido = new JTextField();
		input_newapellido.setBounds(38, 365, 194, 30);
		contentPane.add(input_newapellido);
		input_newapellido.setColumns(10);

		// TEXTO NUEVO EMAIL

		JLabel text_newemail = new JLabel("Nuevo Email");
		text_newemail.setBounds(38, 406, 194, 20);
		contentPane.add(text_newemail);

		// INPUT NUEVO EMAIL

		input_newemail = new JTextField();
		input_newemail.setBounds(38, 437, 194, 30);
		contentPane.add(input_newemail);
		input_newemail.setColumns(10);

		// TEXTO NUEVO DNI

		JLabel text_newdni = new JLabel("Nuevo DNI");
		text_newdni.setBounds(436, 262, 194, 14);
		contentPane.add(text_newdni);

		// INPUT NUEVO DNI

		input_newdni = new JTextField();
		input_newdni.setBounds(436, 287, 194, 31);
		contentPane.add(input_newdni);
		input_newdni.setColumns(10);

		// TEXTO NUEVA CONTRASEÑA

		JLabel text_newpassword = new JLabel("Nueva Contraseña");
		text_newpassword.setBounds(436, 338, 194, 20);
		contentPane.add(text_newpassword);

		// INPUT NUEVA CONTRASEÑA

		input_newcontraseña = new JTextField();
		input_newcontraseña.setBounds(436, 369, 194, 30);
		contentPane.add(input_newcontraseña);
		input_newcontraseña.setColumns(10);

		// TEXTO NUEVO USUARIO

		JLabel text_new_tipousuario = new JLabel("Cambio de usuario");
		text_new_tipousuario.setBounds(436, 401, 265, 30);
		contentPane.add(text_new_tipousuario);

		// OPCIONES NUEVO TIPO DE USUARIO

		JComboBox opciones_newtipousuario = new JComboBox();
		opciones_newtipousuario.setModel(new DefaultComboBoxModel(new String[] { "Cliente", "Profesor", "ADMIN" }));
		opciones_newtipousuario.setBounds(436, 437, 194, 27);
		contentPane.add(opciones_newtipousuario);

		// TEXT SELECCION

		text_seleccionado = new JLabel("Seleccionado: ");
		text_seleccionado.setBounds(54, 151, 620, 94);
		contentPane.add(text_seleccionado);
		text_seleccionado.setText(" Nombre: " + usuario.getNombre() + " Apellido: " + usuario.getApellido() + " Email: "
				+ usuario.getEmail() + " DNI: " + usuario.getDni() + " Contraseña: " + usuario.getPassword()
				+ " Tipo de Usuario: " + usuario.getTipousuario());

		// TEXTO ERROR

		text_error = new JLabel("Errores: ");
		text_error.setBounds(118, 478, 620, 94);
		contentPane.add(text_error);

		// BOTON PARA ACTUALIZAR EL USUARIO

		JButton buttom_updateuser = new JButton("ACTUALIZAR USUARIO");
		buttom_updateuser.setForeground(new Color(0, 128, 64));
		buttom_updateuser.setBounds(133, 583, 432, 67);
		contentPane.add(buttom_updateuser);
		buttom_updateuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dni = Integer.parseInt((input_newdni.getText().isEmpty()) ? "0" : input_newdni.getText());
				String tipousuario = opciones_newtipousuario.getSelectedItem().toString();
				String errores = "";
				if (!input_newnombre.getText().isEmpty()) {
					usuario.setNombre(input_newnombre.getText());
				}
				if (!input_newapellido.getText().isEmpty()) {
					usuario.setApellido(input_newapellido.getText());
				}
				if (!input_newemail.getText().isEmpty()) {
					usuario.setEmail(input_newemail.getText());
				}
				if (!input_newcontraseña.getText().isEmpty()) {
					usuario.setPassword(input_newcontraseña.getText());
				}
				if (!input_newdni.getText().isEmpty()) {
					if (dni < 1000000 || dni > 99999999) {
						text_error.setText("- DNI inválido, debe tener entre 7 y 8 dígitos - ");
						errores= " dni invalido";
					} else {
						usuario.setDni(dni);
					}
				}
				if (!tipousuario.isEmpty()) {
					usuario.setTipousuario(tipousuario);
				}
				Administrador.UpdateUsuario(usuario);
				if (errores.equals("")) {
					AdminMenu adminmenu = new AdminMenu();
					adminmenu.run();
					dispose();
				}
			}
		});

		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_updateuser.setFont(bebasNeue);
			buttom_updateuser.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			text_new_nombre.setFont(roboto);
			text_newapellido.setFont(roboto);
			text_newdni.setFont(roboto);
			text_newemail.setFont(roboto);
			text_newpassword.setFont(roboto);
			text_new_tipousuario.setFont(roboto);
			input_newnombre.setFont(roboto);
			input_newapellido.setFont(roboto);
			input_newdni.setFont(roboto);
			input_newemail.setFont(roboto);
			input_newcontraseña.setFont(roboto);
			opciones_newtipousuario.setFont(roboto);

			InputStream is3 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto2 = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 15f);

			InputStream is4 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue2 = Font.createFont(Font.TRUETYPE_FONT, is4).deriveFont(Font.PLAIN, 20f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
