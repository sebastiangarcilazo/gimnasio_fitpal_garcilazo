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

import BLL.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ProfesorMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void run() {
		try {
			ProfesorMenu frame = new ProfesorMenu();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ProfesorMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 677);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// BOTON VOLVER

		JButton buttom_back = new JButton("VOLVER");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 19, 156, 51);
		contentPane.add(buttom_back);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel mainpanel = new MainPanel();
				mainpanel.run();
				dispose();
			}
		});

		// MAIN ICON

		JLabel main_icon = new JLabel("");
		main_icon.setIcon(new ImageIcon(ProfesorMenu.class.getResource("/resource/fitpal_logo.png")));
		main_icon.setBounds(294, 357, 46, 14);
		contentPane.add(main_icon);

		main_icon.setBounds(173, 162, 323, 332);
		contentPane.add(main_icon);

		// ESCALAR IMAGEN ICON

		ImageIcon icon = new ImageIcon(getClass().getResource("/resource/fitpal_logo.png"));
		Image image = icon.getImage().getScaledInstance(main_icon.getWidth(), main_icon.getHeight(),
				Image.SCALE_SMOOTH);
		main_icon.setIcon(new ImageIcon(image));

		// TITLE PROFESOR

		JLabel title_profesor = new JLabel("Menú de Profesor");
		title_profesor.setBounds(189, 108, 307, 67);
		contentPane.add(title_profesor);

		// OPCIONES PROFESOR

		JComboBox opciones_profesor = new JComboBox();
		opciones_profesor.setModel(new DefaultComboBoxModel(
				new String[] { "Añadir Ejercicio", "Eliminar/Editar Ejercicio", "Crear Subscripcion" }));
		opciones_profesor.setBounds(210, 463, 266, 43);
		String opcionelegida_profesor = opciones_profesor.getSelectedItem().toString();
		contentPane.add(opciones_profesor);

		// BOTON DE LISTO

		JButton buttom_submit_profesor = new JButton("LISTO");
		buttom_submit_profesor.setForeground(new Color(0, 128, 64));
		buttom_submit_profesor.setBounds(210, 551, 266, 51);
		buttom_submit_profesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcionelegida_profesor = (String) opciones_profesor.getSelectedItem();
				if (opcionelegida_profesor.equals("Añadir Ejercicio")) {
					ProfesorEjercicio profesorejercicio = new ProfesorEjercicio();
					profesorejercicio.run();
					dispose();
				}
				if (opcionelegida_profesor.equals("Eliminar/Editar Ejercicio")) {
					ProfesorTable profesortable = new ProfesorTable();
					profesortable.run();
					dispose();
				}
				if (opcionelegida_profesor.equals("Crear Subscripcion")) {
					SubscripcionPanel subscripcionpanel = new SubscripcionPanel();
					subscripcionpanel.run();
				}
				dispose();
			}
		});
		contentPane.add(buttom_submit_profesor);

		// BLOQUE PARA AÑADIR FUENTES

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_profesor.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);
			buttom_submit_profesor.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			opciones_profesor.setFont(roboto);

		} catch (Exception e) {
			e.printStackTrace();
			title_profesor.setFont(new Font("SansSerif", Font.BOLD, 24));
		}
	}
}
