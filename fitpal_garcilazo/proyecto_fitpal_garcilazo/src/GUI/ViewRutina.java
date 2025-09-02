package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;

import java.awt.Color;
import javax.swing.JLabel;

public class ViewRutina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea text_rutina;
	public void run(Cliente cliente) {
		try {
			ViewRutina frame = new ViewRutina(cliente);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ViewRutina(Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 222, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// TITLE RUTINA
		
		JLabel title_rutina = new JLabel("TU SEGUIMIENTO DE RUTINA");
		title_rutina.setBounds(44, -12, 537, 122);
		contentPane.add(title_rutina);
		
		// BOTON VOLVER

		JButton buttom_back = new JButton("Volver");
		buttom_back.setForeground(new Color(0, 128, 192));
		buttom_back.setBounds(10, 411, 179, 48);
		buttom_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteMenu clientemenu = new ClienteMenu(cliente);
				clientemenu.run(cliente);
				dispose();
			}
		});
		contentPane.add(buttom_back);
		
		// TEXTO RUTINA
		
		text_rutina = new JTextArea();
		text_rutina.setLineWrap(true);
		text_rutina.setWrapStyleWord(true);
		text_rutina.setEditable(false);
		text_rutina.setText(cliente.getRutina());
		
		JScrollPane scrollTextRutina = new JScrollPane(text_rutina);
		scrollTextRutina.setBounds(125, 100, 250, 300);
		contentPane.add(scrollTextRutina);
		
		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_rutina.setFont(bebasNeue);
			buttom_back.setFont(bebasNeue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
