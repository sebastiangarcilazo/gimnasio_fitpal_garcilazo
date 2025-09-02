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

import BLL.Cliente;
import BLL.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JButton;

public class ClienteMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cliente cliente;
	private JLabel text_error;
	private JLabel text_info;

	public static void run(Cliente cliente) {
		try {
			ClienteMenu frame = new ClienteMenu(cliente);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClienteMenu(Cliente cliente) {
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
		buttom_back.setBounds(10, 7, 179, 48);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainLogin mainlogin = new MainLogin();
				mainlogin.run();
				dispose();
			}
		});
		contentPane.add(buttom_back);

		// CARGAR IMAGEN

		JLabel imagen_icono = new JLabel("");
		imagen_icono.setIcon(new ImageIcon(ClienteMenu.class.getResource("/resource/fitpal_logo.png")));
		imagen_icono.setBounds(136, 7, 418, 385);
		ImageIcon icon = new ImageIcon(getClass().getResource("/resource/fitpal_logo.png"));
		Image image = icon.getImage().getScaledInstance(418, 385, Image.SCALE_SMOOTH);
		imagen_icono.setIcon(new ImageIcon(image));
		contentPane.add(imagen_icono);

		// ESCALAR IMAGEN ICON

		JLabel title_cliente = new JLabel("CLIENTE DE FITPAL");
		title_cliente.setBounds(207, 364, 266, 59);
		contentPane.add(title_cliente);

		// OPCIONES CLIENTE

		JComboBox<String> opciones_cliente = new JComboBox<>();
		opciones_cliente.setBackground(new Color(255, 255, 255));
		opciones_cliente.setForeground(new Color(0, 0, 0));
		opciones_cliente.setModel(new DefaultComboBoxModel<>(new String[] { "Registrar Progreso",
				"Renovar Suscripcion", "Cancelar Suscripcion", "Ver Seguimiento de Rutina" }));
		opciones_cliente.setBounds(177, 470, 322, 45);
		contentPane.add(opciones_cliente);

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(136, 415, 664, 63);
		contentPane.add(text_error);

		// TEXTO DE INFO

		JLabel text_info = new JLabel("");
		text_info.setBounds(175, 509, 664, 63);
		contentPane.add(text_info);
		
		// BOTON LISTO

				JButton buttom_submit = new JButton("LISTO");
				buttom_submit.setForeground(new Color(0, 128, 255));
				buttom_submit.setBounds(213, 567, 235, 70);
				buttom_submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String opcion_cliente = opciones_cliente.getSelectedItem().toString();
						if (opcion_cliente.equalsIgnoreCase("Registrar Progreso")
								&& cliente.getSubsestado().equalsIgnoreCase("Vigente")) {
							RegistrarEntrenamiento registrarentrenamiento = new RegistrarEntrenamiento(cliente);
							registrarentrenamiento.run(cliente);
							dispose();
						}
						if (opcion_cliente.equalsIgnoreCase("Renovar Suscripcion")) {
							Cliente.UpdateSubs(cliente);
							text_info.setText("Suscripión RENOVADA Correctamente");
							cliente.setSubsestado("Vigente");
						}
						if (opcion_cliente.equalsIgnoreCase("Cancelar Suscripcion")) {
							Cliente.CancelSubs(cliente);
							text_info.setText("Suscripión CANCELADA Correctamente");
							cliente.setSubsestado("Cancelada");
						}
						if (opcion_cliente.equalsIgnoreCase("Ver Seguimiento de Rutina")) {
							ViewRutina viewrutina = new ViewRutina(cliente);
							viewrutina.run(cliente);
							dispose();
						}
					}
				});
				contentPane.add(buttom_submit);
		
		
		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_cliente.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);
			buttom_submit.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			opciones_cliente.setFont(roboto);
			text_error.setFont(roboto);
			text_info.setFont(roboto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
