package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrativo;
import logico.Comercial;
import logico.Personal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroDePersonal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCedula;
	private JTextField txtCorreo;
	private JTextField txtUsuario;
	private JRadioButton rdtbnComercial;
	private JRadioButton rdbtnAdm;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDepartamento;
	private JPasswordField pwClave;
	private JComboBox cbxZona;
	private JComboBox cbxCargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroDePersonal frame = new RegistroDePersonal();
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
	public RegistroDePersonal() {
		setTitle("Registrar Personal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegistroDePersonal.class.getResource("/images/miniatura.png")));
		lblNewLabel.setBounds(36, 11, 52, 53);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("S\u00E9 uno de nuestros profesionales!");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(98, 30, 222, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(36, 75, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(36, 90, 216, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(36, 121, 69, 14);
		contentPane.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(36, 135, 216, 20);
		contentPane.add(txtApellidos);

		JLabel lblNewLabel_3 = new JLabel("C\u00E9dula");
		lblNewLabel_3.setBounds(303, 75, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtCedula = new JTextField();
		txtCedula.setBounds(303, 90, 148, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_4.setBounds(303, 138, 127, 14);
		contentPane.add(lblNewLabel_4);

		JSpinner spnNacimiento = new JSpinner();
		spnNacimiento.setModel(new SpinnerDateModel(new Date(1795492800000L), null, null, Calendar.DAY_OF_MONTH));
		spnNacimiento.setBounds(430, 135, 86, 20);
		contentPane.add(spnNacimiento);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spnNacimiento, "dd/MM/yyyy");
		spnNacimiento.setEditor(editor);

		JLabel lblNewLabel_5 = new JLabel("Correo Electr\u00F3nico");
		lblNewLabel_5.setBounds(36, 166, 127, 14);
		contentPane.add(lblNewLabel_5);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(36, 182, 415, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Genera tu usuario");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_6.setBounds(98, 232, 154, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Nombre de Usuario");
		lblNewLabel_7.setBounds(36, 268, 127, 14);
		contentPane.add(lblNewLabel_7);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(138, 265, 169, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Clave");
		lblNewLabel_8.setBounds(36, 296, 46, 14);
		contentPane.add(lblNewLabel_8);

		JLabel lblTipoDeTrabajador = new JLabel("Tipo de Trabajador");
		lblTipoDeTrabajador.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTipoDeTrabajador.setBounds(98, 351, 154, 14);
		contentPane.add(lblTipoDeTrabajador);

		rdtbnComercial = new JRadioButton("Comercial");
		rdtbnComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbxZona.setEnabled(true);
				txtDepartamento.setEnabled(false);
				cbxCargo.setEnabled(false);
			}
		});
		rdtbnComercial.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdtbnComercial);
		rdtbnComercial.setBounds(36, 383, 109, 23);
		contentPane.add(rdtbnComercial);

		rdbtnAdm = new JRadioButton("Administrativo");
		rdbtnAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtDepartamento.setEnabled(true);
				cbxCargo.setEnabled(true);
				cbxZona.setEnabled(false);
			}
		});
		rdbtnAdm.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnAdm);
		rdbtnAdm.setBounds(321, 383, 109, 23);
		contentPane.add(rdbtnAdm);

		JLabel lblNewLabel_9 = new JLabel("Zona Asignada");
		lblNewLabel_9.setBounds(36, 435, 148, 14);
		contentPane.add(lblNewLabel_9);

		cbxZona = new JComboBox();
		cbxZona.setEnabled(false);
		cbxZona.setBounds(36, 452, 175, 20);
		contentPane.add(cbxZona);

		JLabel lblNewLabel_10 = new JLabel("Departamento");
		lblNewLabel_10.setBounds(321, 413, 109, 14);
		contentPane.add(lblNewLabel_10);

		txtDepartamento = new JTextField();
		txtDepartamento.setEnabled(false);
		txtDepartamento.setBounds(321, 432, 179, 20);
		contentPane.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Cargo");
		lblNewLabel_11.setBounds(321, 463, 46, 14);
		contentPane.add(lblNewLabel_11);

		cbxCargo = new JComboBox();
		cbxCargo.setEnabled(false);
		cbxCargo.setBounds(356, 463, 144, 20);
		contentPane.add(cbxCargo);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar el registro?",
						"Confirmar cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				if (opcion == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnCancelar.setBounds(464, 527, 89, 23);
		contentPane.add(btnCancelar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombre.getText().trim();
				String apellidos = txtApellidos.getText().trim();
				String cedula = txtCedula.getText().trim();
				String correo = txtCorreo.getText().trim();
				Date fechaNac = (Date) spnNacimiento.getValue();
				String usuario = txtUsuario.getText().trim();
				String clave = new String(pwClave.getPassword()); 

				if (nombre.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (!rdtbnComercial.isSelected() && !rdbtnAdm.isSelected()) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de trabajador", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Personal nuevoPersonal = null;

				if (rdtbnComercial.isSelected()) {
				    String zona = cbxZona.getSelectedItem().toString();
				    String tipo = rdtbnComercial.getText();
				    nuevoPersonal = new Comercial(1, nombre, apellidos, cedula, fechaNac, correo, "", 0.0, true, usuario, clave, null,0.0, zona, 0.0, tipo);

				} else if (rdbtnAdm.isSelected()) {
					String tipo = rdbtnAdm.getText(); 
				    String departamento = txtDepartamento.getText().trim();
				    String cargo = cbxCargo.getSelectedItem().toString();

				    if (departamento.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Debe ingresar el departamento", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
				        return;
				    }
				    nuevoPersonal = new Administrativo(1, nombre, apellidos, cedula, fechaNac, correo,"", 0.0, true, usuario, clave, null,departamento, cargo, tipo);
				}
			}
			
		});
		btnRegistrar.setBounds(356, 527, 89, 23);
		contentPane.add(btnRegistrar);

		pwClave = new JPasswordField();
		pwClave.setBounds(138, 293, 169, 20);
		contentPane.add(pwClave);
	}
}
