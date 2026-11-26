package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cable;
import logico.Internet;
import logico.Movistar;
import logico.Servicio;
import logico.Telefono;

import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionDeServicios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID_Servicio;
	private JTextField txtNombreServicio;
	private JTextField txtPrecio;
	private JButton btnModificar;
	private JPanel panelCambiante;
	private JComboBox comboBox_Tipo;
	private JComboBox comboBox_Tipo_Internet;
	private JTextField txtCantidad_Canales;
	private JTextField txtVelocidad_Subida;
	private JTextField txtVelocidad_Bajada;
	private JTextField txtNumTelefonico;
	private JTextField txtCantMinutos;
	private JTable table;
	public static DefaultTableModel model;
	public static Object row[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionDeServicios dialog = new GestionDeServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionDeServicios() {
		setTitle("Gesti\u00F3n de Servicios");
		setBounds(100, 100, 588, 625);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelCatalogo = new JPanel();
		panelCatalogo.setBounds(12, 59, 547, 203);
		panelCatalogo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelCatalogo);
		panelCatalogo.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelCatalogo.add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		{
			String headers[] = {"ID","Nombre","Tipo","Precio"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(headers);
			table = new JTable();
			
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(model);
			scrollPane.setViewportView(table);
		}
		panelCatalogo.add(scrollPane, BorderLayout.NORTH);
		{
			JLabel lblNewLabel = new JLabel("Catalogo de Servicios");
			lblNewLabel.setBounds(12, 30, 202, 16);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Datos de Servicio:");
			lblNewLabel_1.setBounds(12, 290, 235, 16);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("ID:");
			lblNewLabel_2.setBounds(12, 327, 22, 16);
			contentPanel.add(lblNewLabel_2);
		}
		
		txtID_Servicio = new JTextField();
		txtID_Servicio.setBounds(36, 324, 99, 22);
		txtID_Servicio.setEditable(false);
		contentPanel.add(txtID_Servicio);
		txtID_Servicio.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setBounds(147, 327, 56, 16);
		contentPanel.add(lblNewLabel_3);
		
		txtNombreServicio = new JTextField();
		txtNombreServicio.setBounds(203, 324, 169, 22);
		contentPanel.add(txtNombreServicio);
		txtNombreServicio.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo:");
		lblNewLabel_4.setBounds(384, 327, 39, 16);
		contentPanel.add(lblNewLabel_4);
		
		comboBox_Tipo = new JComboBox();
		comboBox_Tipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipoSeleccionado = (String) comboBox_Tipo.getSelectedItem();
				CardLayout cl = (CardLayout) panelCambiante.getLayout();
				cl.show(panelCambiante, tipoSeleccionado);
			}
		});
		comboBox_Tipo.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Teléfono", "Cable", "Internet"}));
		comboBox_Tipo.setBounds(421, 324, 138, 22);
		contentPanel.add(comboBox_Tipo);
		
		JLabel lblNewLabel_6 = new JLabel("Precio:");
		lblNewLabel_6.setBounds(12, 470, 44, 16);
		contentPanel.add(lblNewLabel_6);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(57, 467, 130, 22);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar Servicio");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombreServicio.getText().trim();
		        String tipo = (String) comboBox_Tipo.getSelectedItem();

		        if (nombre.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Escribe el nombre del servicio");
		            return;
		        }
		        if (tipo.equals("<<Seleccione>>")) {
		            JOptionPane.showMessageDialog(null, "Selecciona el tipo de servicio");
		            return;
		        }

		        try {
		            int id = Integer.parseInt(txtID_Servicio.getText());
		            double precioBase = Double.parseDouble(txtPrecio.getText().trim());
		            Servicio nuevo;

		            if (tipo.equals("Cable")) {
		                int canales = Integer.parseInt(txtCantidad_Canales.getText().trim());
		                nuevo = new Cable(id, nombre, precioBase, canales);
		            } else if (tipo.equals("Internet")) {
		                int subida = Integer.parseInt(txtVelocidad_Subida.getText().trim());
		                int bajada = Integer.parseInt(txtVelocidad_Bajada.getText().trim());
		                String tipoInternet = (String) comboBox_Tipo_Internet.getSelectedItem();
		                nuevo = new Internet(id, nombre, precioBase, subida, bajada, tipoInternet);
		            } else {
		                String numero = txtNumTelefonico.getText().trim();
		                if (numero.isEmpty()) {
		                    JOptionPane.showMessageDialog(null, "Escribe el número telefónico");
		                    return;
		                }
		                int minutos = Integer.parseInt(txtCantMinutos.getText().trim());
		                nuevo = new Telefono(id, nombre, precioBase, numero, minutos);
		            }

		            if (!Movistar.getInstance().registrarServicio(nuevo)) {
		                JOptionPane.showMessageDialog(null, "No se pudo registrar el servicio");
		                return;
		            }
		            Movistar.getInstance().guardarDatos();
		            loadServicios();
		            limpiarFormulario();
		            JOptionPane.showMessageDialog(null, "Servicio registrado");
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null,
		                    "Revisa que el precio, los canales, las velocidades y los minutos sean números");
		        }
			}
		});
		btnRegistrar.setBounds(36, 512, 138, 25);
		contentPanel.add(btnRegistrar);
		
		btnModificar = new JButton("Modificar Servicio");
		btnModificar.setBounds(213, 512, 138, 25);
		contentPanel.add(btnModificar);
		
		JButton btnDesactivar = new JButton("Desactivar Servicio");
		btnDesactivar.setBounds(384, 512, 150, 25);
		contentPanel.add(btnDesactivar);
	
		CardLayout Cambiante = new CardLayout();
		panelCambiante = new JPanel(Cambiante);
		
		panelCambiante = new JPanel();
		panelCambiante.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCambiante.setBounds(12, 359, 547, 88);
		contentPanel.add(panelCambiante);
		panelCambiante.setLayout(new CardLayout(0, 0));
		
		JPanel panel_Vacio = new JPanel();
		panelCambiante.add(panel_Vacio, "<<Seleccione>>");
		panel_Vacio.setLayout(null);
		
		JPanel panel_Telefonico = new JPanel();
		panelCambiante.add(panel_Telefonico, "Teléfono");
		panel_Telefonico.setLayout(null);
		
		JLabel label_4 = new JLabel("Numero Tel\u00E9fonico:");
		label_4.setBounds(12, 13, 113, 16);
		panel_Telefonico.add(label_4);
		
		txtNumTelefonico = new JTextField();
		txtNumTelefonico.setColumns(10);
		txtNumTelefonico.setBounds(12, 40, 241, 22);
		panel_Telefonico.add(txtNumTelefonico);
		
		txtCantMinutos = new JTextField();
		txtCantMinutos.setColumns(10);
		txtCantMinutos.setBounds(294, 40, 241, 22);
		panel_Telefonico.add(txtCantMinutos);
		
		JLabel label_5 = new JLabel("Cantidad de Minutos:");
		label_5.setBounds(294, 13, 121, 16);
		panel_Telefonico.add(label_5);
		
		JPanel panel_Internet = new JPanel();
		panelCambiante.add(panel_Internet, "Internet");
		panel_Internet.setLayout(null);
		
		JLabel label_1 = new JLabel("Velocidad de Subida:");
		label_1.setBounds(12, 16, 128, 16);
		panel_Internet.add(label_1);
		
		txtVelocidad_Subida = new JTextField();
		txtVelocidad_Subida.setColumns(10);
		txtVelocidad_Subida.setBounds(137, 13, 128, 22);
		panel_Internet.add(txtVelocidad_Subida);
		
		JLabel label_2 = new JLabel("Velocidad de Bajada:");
		label_2.setBounds(280, 16, 128, 16);
		panel_Internet.add(label_2);
		
		txtVelocidad_Bajada = new JTextField();
		txtVelocidad_Bajada.setColumns(10);
		txtVelocidad_Bajada.setBounds(406, 13, 129, 22);
		panel_Internet.add(txtVelocidad_Bajada);
		
		comboBox_Tipo_Internet = new JComboBox();
		comboBox_Tipo_Internet.setModel(new DefaultComboBoxModel(new String[] {"Fibra", "Cobre"}));
		comboBox_Tipo_Internet.setBounds(136, 52, 129, 22);
		panel_Internet.add(comboBox_Tipo_Internet);
		
		JLabel label_3 = new JLabel("Tipo de Internet:");
		label_3.setBounds(12, 55, 112, 16);
		panel_Internet.add(label_3);
		
		JPanel panel_Cable = new JPanel();
		panelCambiante.add(panel_Cable, "Cable");
		panel_Cable.setLayout(null);
		
		JLabel label = new JLabel("Cantidad de Canales:");
		label.setBounds(12, 13, 138, 16);
		panel_Cable.add(label);
		
		txtCantidad_Canales = new JTextField();
		txtCantidad_Canales.setColumns(10);
		txtCantidad_Canales.setBounds(12, 42, 262, 22);
		panel_Cable.add(txtCantidad_Canales);
		
		loadServicios();
		txtID_Servicio.setText(String.valueOf(siguienteId()));
	}
	
	public void loadServicios() {
	    model.setRowCount(0);
	    row = new Object[model.getColumnCount()];
	    for (Servicio aux : Movistar.getInstance().listarServicios()) {
	            row[0] = aux.getId();
	            row[1] = aux.getNombre();
	            String tipo;
	            if (aux instanceof Cable) {
	                tipo = "Cable";
	            } else if (aux instanceof Internet) {
	                tipo = "Internet";
	            } else {
	                tipo = "Teléfono";
	            }
	            row[2] = tipo;
	            row[3] = aux.getPrecioIndividual();
	            model.addRow(row);
	    }
	}
	
	private int siguienteId() {
	    int max = 0;
	    for (Servicio s : Movistar.getInstance().listarServicios()) {
	        if (s.getId() > max) {
	            max = s.getId();
	        }
	    }
	    return max + 1;
	}

	private void limpiarFormulario() {
	    txtNombreServicio.setText("");
	    txtPrecio.setText("");
	    txtCantidad_Canales.setText("");
	    txtVelocidad_Subida.setText("");
	    txtVelocidad_Bajada.setText("");
	    txtNumTelefonico.setText("");
	    txtCantMinutos.setText("");
	    comboBox_Tipo.setSelectedIndex(0);
	    txtID_Servicio.setText(String.valueOf(siguienteId()));
	}
}
