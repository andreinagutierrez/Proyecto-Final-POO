package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Movistar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream movistar;
				FileOutputStream movistar2;
				ObjectInputStream movistarRead;
				ObjectOutputStream movistarWrite;
				
				try {
					movistar = new FileInputStream (Movistar.RUTA_DAT);
					movistarRead = new ObjectInputStream(movistar);
					Movistar temp = (Movistar)movistarRead.readObject();
					Movistar.setInstance(temp);
					movistar.close();
					movistarRead.close();
				} catch (FileNotFoundException e) {
					try {
						Movistar nueva = new Movistar();
			            nueva.cargarDatosIniciales();
						movistar2 = new  FileOutputStream(Movistar.RUTA_DAT);
						movistarWrite = new ObjectOutputStream(movistar2);

						Movistar.setInstance(nueva);
						movistarWrite.writeObject(Movistar.getInstance());
						movistarWrite.close();
						movistar2.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 353);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/images/user (1).png")));
		lblNewLabel.setBounds(148, 28, 64, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setBounds(62, 102, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(62, 133, 225, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUsuario.getText();
				String clave = new String(passwordText.getPassword());
				
				if(Movistar.getInstance().confirmarSesion(user, clave)) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					 JOptionPane.showMessageDialog(login.this, "Usuario o contraseña incorrectos",
			                    "Error", JOptionPane.ERROR_MESSAGE);
			            passwordText.setText("");
				}
			}
		});
		btnLogin.setBounds(283, 268, 72, 25);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2.setBounds(62, 168, 78, 16);
		contentPane.add(lblNewLabel_2);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(62, 197, 225, 22);
		contentPane.add(passwordText);
	}
}
