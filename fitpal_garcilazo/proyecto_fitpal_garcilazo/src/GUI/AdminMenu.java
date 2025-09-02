package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Administrador;
import BLL.Ejercicio;
import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Usuario usuarioSeleccionado;
	private JLabel text_seleccionado;
	private JLabel text_error;

	public void run() {
		try {
			AdminMenu frame = new AdminMenu();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 197));
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
				MainLogin mainlogin = new MainLogin();
				mainlogin.run();
				dispose();
			}
		});

		// ADMIN TITLE

		JLabel title_admin = new JLabel("ADMIN");
		title_admin.setForeground(new Color(255, 0, 0));
		title_admin.setBounds(284, 0, 138, 75);
		contentPane.add(title_admin);

		// TEXT SELECCION

		text_seleccionado = new JLabel("Seleccionado: ");
		text_seleccionado.setBounds(30, 466, 620, 25);
		contentPane.add(text_seleccionado);

		// TEXTO DE ERROR

		JLabel text_error = new JLabel("");
		text_error.setBounds(84, 609, 664, 63);
		contentPane.add(text_error);

		// BOTON EDITAR

		JButton buttom_edit = new JButton("Editar");
		buttom_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioSeleccionado != null && !text_seleccionado.getText().isEmpty()) {
					UpdateUsuarios updateusuarios = new UpdateUsuarios(usuarioSeleccionado);
					updateusuarios.run(usuarioSeleccionado);
					dispose();
				} else {
					text_error.setText(" Para editar debes seleccionar un usuario primero ");
				}
			}
		});
		buttom_edit.setBackground(new Color(255, 255, 255));
		buttom_edit.setForeground(new Color(0, 128, 192));
		buttom_edit.setBounds(385, 514, 219, 105);
		contentPane.add(buttom_edit);

		// BOTON ELIMINAR

		JButton buttom_delete = new JButton("Eliminar");
		buttom_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioSeleccionado != null && !text_seleccionado.getText().isEmpty()) {
					Administrador.EliminarUsuario(usuarioSeleccionado);
					cargarTabla();
				} else {
					text_error.setText(" Para ELIMINAR debes seleccionar un usuario primero ");
				}

			}
		});
		buttom_delete.setForeground(new Color(255, 0, 0));
		buttom_delete.setBackground(new Color(255, 255, 255));
		buttom_delete.setBounds(51, 514, 227, 105);
		contentPane.add(buttom_delete);

		// TEXTO USUARIO SELECCIONADO

		JLabel text_usuario_seleccionado = new JLabel("Usuario seleccionado:");
		text_usuario_seleccionado.setForeground(new Color(0, 0, 0));
		text_usuario_seleccionado.setBounds(21, 404, 180, 33);
		contentPane.add(text_usuario_seleccionado);

		// TABLE

		model = new DefaultTableModel(
				new String[] { "id_usuario", "nombre", "apellido", "email", "password", "dni", "tipousuario" }, 0);
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 70, 620, 300);
		contentPane.add(scrollPane);
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					usuarioSeleccionado = new Usuario((int) model.getValueAt(row, 0), (String) model.getValueAt(row, 1),
							(String) model.getValueAt(row, 2), (String) model.getValueAt(row, 3),
							(String) model.getValueAt(row, 4), (int) model.getValueAt(row, 5),
							(String) model.getValueAt(row, 6));
					text_seleccionado.setText(usuarioSeleccionado.toString());
				}
			}
		});

		cargarTabla();

		// BLOQUE QUE ASIGNA FONTS

		try {
			InputStream is1 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue = Font.createFont(Font.TRUETYPE_FONT, is1).deriveFont(Font.PLAIN, 48f);
			title_admin.setFont(bebasNeue);
			buttom_delete.setFont(bebasNeue);
			buttom_edit.setFont(bebasNeue);

			InputStream is2 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 20f);
			InputStream is3 = getClass().getResourceAsStream("/resource/Roboto-VariableFont_wdth,wght.ttf");
			Font roboto2 = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 15f);
			text_seleccionado.setFont(roboto2);
			text_usuario_seleccionado.setFont(roboto2);

			InputStream is4 = getClass().getResourceAsStream("/resource/BebasNeue-Regular.ttf");
			Font bebasNeue2 = Font.createFont(Font.TRUETYPE_FONT, is4).deriveFont(Font.PLAIN, 20f);
			buttom_back.setFont(bebasNeue2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CARGA LA TABLA DE USUARIOS

	private void cargarTabla() {
		model.setRowCount(0);
		Usuario usuario = new Usuario(0, "", "", "", "", 0, "");
		LinkedList<Usuario> usuarios = ControllerUsuario.mostrarUsuarios();
		for (Usuario u : usuarios) {
			model.addRow(new Object[] { u.getId(), u.getNombre(), u.getApellido(), u.getEmail(), u.getPassword(),
					u.getDni(), u.getTipousuario() });
		}
	}

}
