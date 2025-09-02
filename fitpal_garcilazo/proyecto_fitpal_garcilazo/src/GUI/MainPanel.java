package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel main_icon;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainPanel frame = new MainPanel();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void run() {
		EventQueue.invokeLater(() -> {
			try {
				MainPanel frame = new MainPanel();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		
		// PANEL PRINCIPAL

		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		setContentPane(contentPane);

		// TITLE

		JLabel title = new JLabel("THE BEST GYM");
		title.setForeground(new Color(192, 192, 192));
		title.setBounds(233, 361, 390, 40);
		contentPane.add(title);

		// BOTÓN LOGIN

		JButton buttom_login = new JButton("INICIAR SESION");
		buttom_login.setForeground(new Color(0, 128, 192));
		buttom_login.setBackground(new Color(192, 192, 192));
		buttom_login.setBounds(174, 430, 345, 55);
		buttom_login.setHorizontalTextPosition(SwingConstants.LEFT);
		buttom_login.setIconTextGap(10);
		buttom_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainLogin mainlogin = new MainLogin();
				mainlogin.run();
				dispose();
			}
		});
		contentPane.add(buttom_login);

		// MAIN ICON

		main_icon = new JLabel("");
		main_icon.setBounds(97, -55, 500, 456);
		contentPane.add(main_icon);

		// ESCALAR IMAGEN ICON

		ImageIcon icon = new ImageIcon(getClass().getResource("/resource/fitpal_logo.png"));
		Image image = icon.getImage().getScaledInstance(main_icon.getWidth(), main_icon.getHeight(),
				Image.SCALE_SMOOTH);
		main_icon.setIcon(new ImageIcon(image));

		// BOTON PARA REGISTRO

		JButton buttom_register = new JButton("REGISTRARSE");
		buttom_register.setForeground(new Color(0, 128, 192));
		buttom_register.setBackground(new Color(192, 192, 192));
		buttom_register.setIconTextGap(10);
		buttom_register.setHorizontalTextPosition(SwingConstants.LEFT);
		buttom_register.setFont(new Font("Bebas Neue", Font.PLAIN, 48));
		buttom_register.setBounds(174, 496, 345, 55);
		buttom_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainRegister mainregister = new MainRegister();
				mainregister.run();
				dispose();
			}
		});
		contentPane.add(buttom_register);

		// BOTON PARA EXIT

		JButton buttom_exit = new JButton("SALIR");
		buttom_exit.setBackground(new Color(128, 128, 128));
		buttom_exit.setForeground(new Color(255, 0, 0));
		buttom_exit.setBounds(175, 595, 344, 55);
		buttom_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(buttom_exit);

		// BLOQUE PARA AÑADIR FUENTES

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title.setFont(bebasNeue);
			buttom_login.setFont(bebasNeue);
			buttom_register.setFont(bebasNeue);
			buttom_exit.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);

		} catch (Exception e) {
			e.printStackTrace();
			title.setFont(new Font("SansSerif", Font.BOLD, 24));
		}

	}

}
