package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cable;
import logico.Internet;
import logico.Movistar;
import logico.Plan;
import logico.Servicio;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GestionDePlanes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTable table_Serv_Disp;
	private JTable table_Serv_Plan;
	private JButton btnAgregar;
	private JButton btnQuitar;
	public static DefaultTableModel modelDisp;
	public static DefaultTableModel modelPlan;
	public static DefaultTableModel modelLista;
	public static Object row[];
	private ArrayList<Servicio> PaquetePlan = new ArrayList<>();
	private JTable table_Plan_Lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionDePlanes dialog = new GestionDePlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionDePlanes() {
		setTitle("Gesti\u00F3n de Planes");
		setBounds(100, 100, 574, 466);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Registrar Planes", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(12, 13, 26, 16);
		panel.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(43, 10, 123, 22);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(247, 13, 56, 16);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(315, 10, 206, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panel_Servicios = new JPanel();
		panel_Servicios.setBounds(12, 85, 206, 201);
		panel.add(panel_Servicios);
		panel_Servicios.setLayout(new BorderLayout(0, 0));
		
		table_Serv_Disp = new JTable();
		table_Serv_Disp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_Serv_Disp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_Servicios.add(table_Serv_Disp, BorderLayout.CENTER);
		
		JScrollPane scrollPane_Disponible = new JScrollPane();
		scrollPane_Disponible.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel_Servicios.add(scrollPane_Disponible, BorderLayout.NORTH); 
		{
			String headers[] = {"ID","Nombre","Tipo","Precio"};
			modelDisp = new DefaultTableModel();
			modelDisp.setColumnIdentifiers(headers);
			table_Serv_Disp = new JTable();
			
			table_Serv_Disp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_Serv_Disp.setModel(modelDisp);
			scrollPane_Disponible.setViewportView(table_Serv_Disp);
		}
		
		btnAgregar = new JButton(">");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = -1;
				index = table_Serv_Disp.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Selecciona un Servicio");
			        return;
				}
				 int codigo = (int) table_Serv_Disp.getValueAt(index, 0);
				 Servicio s = Movistar.getInstance().buscarServicio(codigo);
				 if(s != null) {
					 PaquetePlan.add(s);
					 loadServicios();
					 loadServicioPlan();
					 actualizarTotal();
				 }
			}
		});
		btnAgregar.setBounds(230, 132, 72, 25);
		panel.add(btnAgregar);
		
		btnQuitar = new JButton("<");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table_Serv_Plan.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Selecciona un servicio del plan");
			        return;
				}
				PaquetePlan.remove(index);
				loadServicios();
				loadServicioPlan();
				actualizarTotal();
			}
		});
		btnQuitar.setBounds(230, 181, 73, 25);
		panel.add(btnQuitar);
		
		JPanel panel_Plan = new JPanel();
		panel_Plan.setBounds(315, 85, 206, 201);
		panel.add(panel_Plan);
		panel_Plan.setLayout(new BorderLayout(0, 0));
		
		table_Serv_Plan = new JTable();
		table_Serv_Plan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_Plan.add(table_Serv_Plan, BorderLayout.CENTER);
		
		JScrollPane scrollPane_Plan = new JScrollPane();
		scrollPane_Plan.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_Plan.add(scrollPane_Plan, BorderLayout.NORTH); 
		{
			String headers[] = {"ID","Nombre","Tipo","Precio"};
			modelPlan = new DefaultTableModel();
			modelPlan.setColumnIdentifiers(headers);
			table_Serv_Plan = new JTable();
			
			table_Serv_Plan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_Serv_Plan.setModel(modelPlan);
			scrollPane_Plan.setViewportView(table_Serv_Plan);
		}
		
		JLabel lblNewLabel_2 = new JLabel("Servicios Disponibles:");
		lblNewLabel_2.setBounds(12, 56, 140, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Servicicios en Plan:");
		lblNewLabel_3.setBounds(315, 56, 123, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Precio:");
		lblNewLabel_4.setBounds(315, 303, 40, 16);
		panel.add(lblNewLabel_4);
		
		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(359, 300, 162, 22);
		panel.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText().trim();
		        if (nombre.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Escribe el nombre del plan");
		            return;
		        }
		        if (PaquetePlan.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "El plan no tiene servicios");
		            return;
		        }

		        int id = Integer.parseInt(txtID.getText());
		        Plan plan = new Plan(id, nombre);
		        for (Servicio s : PaquetePlan) {
		            plan.agregarServicio(s);
		        }

		        if (!Movistar.getInstance().registrarPlan(plan)) {
		            JOptionPane.showMessageDialog(null, "No se pudo registrar el plan");
		            return;
		        }
		        Movistar.getInstance().guardarDatos();

		        String precio = String.format("%.2f", plan.calcularPrecio());
		        PaquetePlan.clear();
		        txtNombre.setText("");
		        loadServicios();
		        loadPlanes();
		        loadServicioPlan();
		        actualizarTotal();
		        txtID.setText(String.valueOf(siguienteId()));
		        
		        JOptionPane.showMessageDialog(null,
		                "Plan registrado\nID: " + id + "\nPrecio: " + precio,
		                "Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnRegistrar.setBounds(424, 336, 97, 25);
		panel.add(btnRegistrar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Lista de Planes", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panelListaPlan = new JPanel();
		panelListaPlan.setBounds(12, 13, 516, 254);
		panel_1.add(panelListaPlan);
		panelListaPlan.setLayout(new BorderLayout(0, 0));
		
		table_Plan_Lista = new JTable();
		table_Plan_Lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		panelListaPlan.add(table_Plan_Lista, BorderLayout.CENTER);
		
		JScrollPane scrollPane_Lista = new JScrollPane();
		scrollPane_Lista.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelListaPlan.add(scrollPane_Lista, BorderLayout.NORTH); 
		{
			String headers[] = {"ID","Nombre","Tipo","Precio"};
			modelLista = new DefaultTableModel();
			modelLista.setColumnIdentifiers(headers);
			table_Plan_Lista = new JTable();
			
			table_Plan_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_Plan_Lista.setModel(modelLista);
			scrollPane_Lista.setViewportView(table_Plan_Lista);
		}
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table_Plan_Lista.getSelectedRow();
		        if (index == -1) {
		            JOptionPane.showMessageDialog(null, "Selecciona un plan");
		            return;
		        }
		        int id = (int) table_Plan_Lista.getValueAt(index, 0);
		        String nombre = (String) table_Plan_Lista.getValueAt(index, 1);

		        int opcion = JOptionPane.showConfirmDialog(null,
		                "¿Eliminar el plan " + nombre + "?", "Confirmar",
		                JOptionPane.YES_NO_OPTION);
		        if (opcion != JOptionPane.YES_OPTION) {
		            return;
		        }

		        if (Movistar.getInstance().eliminarPlan(id)) {
		            Movistar.getInstance().guardarDatos();
		            loadPlanes();
		            txtID.setText(String.valueOf(siguienteId()));
		            JOptionPane.showMessageDialog(null, "Plan eliminado");
		        } else {
		            JOptionPane.showMessageDialog(null,
		                    "No se pudo eliminar el plan. Puede que algún cliente lo tenga contratado.");
		        }
		    }
		});
		btnEliminar.setBounds(431, 280, 97, 25);
		panel_1.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table_Plan_Lista.getSelectedRow();
		        if (index == -1) {
		            JOptionPane.showMessageDialog(null, "Selecciona un plan");
		            return;
		        }
		        int id = (int) table_Plan_Lista.getValueAt(index, 0);
		        Plan plan = Movistar.getInstance().buscarPlan(id);
		        if (plan != null) {
		            GestionDePlanes dialog = new GestionDePlanes();
		            dialog.setModal(true);
		            dialog.setVisible(true);
		            loadPlanes();
		        }
			}
		});
		btnModificar.setBounds(12, 280, 97, 25);
		panel_1.add(btnModificar);
		
		txtID.setText(String.valueOf(siguienteId()));
	    loadServicios();
	    loadPlanes();
	    loadServicioPlan();
	    actualizarTotal();
	}
	
	public void loadServicios() {
	    modelDisp.setRowCount(0);
	    row = new Object[modelDisp.getColumnCount()];
	    for (Servicio aux : Movistar.getInstance().listarServicios()) {
	        if (!PaquetePlan.contains(aux)) {
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
	            modelDisp.addRow(row);
	        }
	    }
	}
	
	public void loadServicioPlan() {
		modelPlan.setRowCount(0);
		row = new Object[modelPlan.getColumnCount()];
		for(Servicio aux: PaquetePlan) {
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
			  modelPlan.addRow(row);
		}
	}
	
	private void actualizarTotal() {
	    float total = 0;
	    for (Servicio s : PaquetePlan) {
	        total += s.getPrecioIndividual();
	    }
	    txtPrecio.setText(String.format("%.2f", total));
	}
	
	private int siguienteId() {
	    int max = 0;
	    for (Plan p : Movistar.getInstance().getLosPlanes()) {
	        if (p.getId() > max) {
	            max = p.getId();
	        }
	    }
	    return max + 1;
	}
	
	public void loadPlanes() {
	    modelLista.setRowCount(0);
	    row = new Object[modelLista.getColumnCount()];
	    for (Plan p : Movistar.getInstance().getLosPlanes()) {
	    	row[0] = p.getId();
	    	row[1] = p.getNombre();
	    	row[2] = p.getServiciosIncluidos().size();
	    	row[3] = String.format("%.2f", p.calcularPrecio());
	        modelLista.addRow(row);
	    }
	}
}
