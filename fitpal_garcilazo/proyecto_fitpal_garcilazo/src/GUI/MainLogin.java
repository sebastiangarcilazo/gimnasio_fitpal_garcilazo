package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Cliente;
import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input_email;
	private JPasswordField input_password;
	private JLabel text_email;
	private JLabel text_password;
	private JLabel title_login;
	private JButton buttom_login;
	private JLabel title;
	private Cliente cliente;

	public void run() {
		try {
			MainLogin frame = new MainLogin();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MainLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel main_icon = new JLabel("");
		main_icon.setBounds(44, 41, 105, 14);
		contentPane.add(main_icon);

		// ESCALAR IMAGEN ICON

		ImageIcon icon = new ImageIcon(getClass().getResource("/resource/fitpal_logo.png"));

		title = new JLabel("INICIO DE SESIÓN");
		title.setBounds(205, 277, 306, 70);
		contentPane.add(title);

		// BOTON VOLVER

		JButton buttom_back = new JButton("Volver");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 7, 179, 48);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel mainpanel = new MainPanel();
				mainpanel.run();
				dispose();
			}
		});
		contentPane.add(buttom_back);

		// TEXTO EMAIL

		text_email = new JLabel("Email");
		text_email.setBounds(197, 399, 257, 14);
		contentPane.add(text_email);

		// TEXTO PASSWORD

		text_password = new JLabel("Contraseña");
		text_password.setBounds(197, 474, 257, 14);
		contentPane.add(text_password);

		// INPUT EMAIL

		input_email = new JTextField();
		input_email.setBounds(195, 421, 259, 30);
		contentPane.add(input_email);
		input_email.setColumns(10);

		// INPUT PASSWORD

		input_password = new JPasswordField();
		input_password.setBounds(197, 499, 257, 30);
		contentPane.add(input_password);

		// TEXTO PARA INFORMAR

		JLabel text_info = new JLabel("");
		text_info.setBounds(162, 522, 413, 63);
		contentPane.add(text_info);

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(162, 335, 413, 63);
		contentPane.add(text_error);

		// BUTTON LOGIN

		JButton buttom_login = new JButton("INICIAR SESION");
		buttom_login.setForeground(new Color(0, 128, 192));
		buttom_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_error.setText("");
				text_info.setText("");
				if (input_email.getText().isEmpty() || input_password.getText().isEmpty()) {
					text_info.setText("Por favor complete todos los campos");
					return;
				} else {
					Usuario usuario = ControllerUsuario.IniciarSesion(input_email.getText(), input_password.getText());
					if (usuario != null) {

						// CASO PROFESOR

						if (usuario.getTipousuario().equalsIgnoreCase("Profesor")) {
							ProfesorMenu profesormenu = new ProfesorMenu();
							profesormenu.run();
						}

						// CASO CLIENTE

						if (usuario.getTipousuario().equalsIgnoreCase("Cliente")) {
							String password = new String(input_password.getPassword());
							Cliente cliente = Administrador.BuscarCliente(input_email.getText(), password);
							if (cliente == null) {
							    System.out.println("No se encontró cliente con esos datos.");
							    text_error.setText("Cliente no encontrado.");
							    return; // evitá seguir si no hay cliente
							}
							System.out.println("Cliente encontrado: " + cliente.getNombre()); // o algún dato visible
							ClienteMenu.run(cliente);
						
						}
						// CASO ADMINISTRADOR

						if (usuario.getTipousuario().equalsIgnoreCase("ADMIN")) {
							AdminMenu adminmenu = new AdminMenu();
							adminmenu.run();
						}
						dispose();

					} else {
						text_error.setText("Email o Contraseña INCORRECTOS");

					}
				}
			}
		});

		// MAIN ICON

		main_icon = new JLabel("");
		main_icon.setBounds(117, -63, 432, 397);
		contentPane.add(main_icon);
		Image image = icon.getImage().getScaledInstance(main_icon.getWidth(), main_icon.getHeight(),
				Image.SCALE_SMOOTH);
		main_icon.setIcon(new ImageIcon(image));
		buttom_login.setBounds(197, 572, 257, 78);
		contentPane.add(buttom_login);

		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			buttom_login.setFont(bebasNeue);
			title.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			input_email.setFont(roboto);
			text_email.setFont(roboto);
			text_password.setFont(roboto);
			text_info.setFont(roboto);
			text_error.setFont(roboto);
			input_password.setFont(roboto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
