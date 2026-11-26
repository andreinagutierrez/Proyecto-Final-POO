package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private Dimension dim;
	private JPopupMenu popupMenu;
	private JPopupMenu popupMenu_Plan_Servicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Principal dialog = new Principal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		setTitle("Pantalla Principal - Movistar");
		setBounds(100, 100, 1034, 618);
		
		dim = getToolkit().getScreenSize(); //tomar el ancho de la pantalla
		//setSize(dim.width, dim.height-30);  //asignarle el tama;o
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Movistar");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
			lblNewLabel.setBounds(411, 49, 246, 65);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/images/logomovistarmini.png")));
			lblNewLabel_1.setBounds(235, 22, 152, 138);
			contentPanel.add(lblNewLabel_1);
		}
		
		JButton btnNewButton = new JButton("Gesti\u00F3n de Personal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				popupMenu.show(btnNewButton, 0, btnNewButton.getHeight());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(68, 217, 157, 23);
		contentPanel.add(btnNewButton);
		
		popupMenu = new JPopupMenu();
		addPopup(btnNewButton, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Personal");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroDePersonal registroPersonal = new RegistroDePersonal();
				registroPersonal.setLocationRelativeTo(null); 
				registroPersonal.setVisible(true);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Lista de Personal");
		popupMenu.add(mntmNewMenuItem_1);
		
		JButton btnControlDeClientes = new JButton("Control de Clientes");
		btnControlDeClientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnControlDeClientes.setBounds(68, 251, 157, 23);
		contentPanel.add(btnControlDeClientes);
		
		popupMenu_Plan_Servicio = new JPopupMenu();
		
		JButton btnPlanesYServicios = new JButton("Planes y Servicios");
		btnPlanesYServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupMenu_Plan_Servicio.show(btnPlanesYServicios, 0, btnPlanesYServicios.getHeight());
			}
		});
		btnPlanesYServicios.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPlanesYServicios.setBounds(68, 289, 157, 23);
		contentPanel.add(btnPlanesYServicios);
		
		
		addPopup(btnPlanesYServicios, popupMenu_Plan_Servicio);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Gesti\u00F3n de Servicios");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionDeServicios servicios = new GestionDeServicios();
				servicios.setLocationRelativeTo(null); 
				servicios.setVisible(true);
			}
		});
		popupMenu_Plan_Servicio.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Gesti\u00F3n de Planes");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionDePlanes planes = new GestionDePlanes();
				planes.setLocationRelativeTo(null); 
				planes.setVisible(true);
			}
		});
		popupMenu_Plan_Servicio.add(mntmNewMenuItem_3);
		
		JButton btnFacturacin = new JButton("Facturaci\u00F3n");
		btnFacturacin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFacturacin.setBounds(68, 323, 157, 23);
		contentPanel.add(btnFacturacin);
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultas.setBounds(68, 357, 157, 23);
		contentPanel.add(btnConsultas);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReportes.setBounds(68, 391, 157, 23);
		contentPanel.add(btnReportes);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(904, 545, 89, 23);
		contentPanel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Principal.class.getResource("/images/imagenderecha.png")));
		lblNewLabel_2.setBounds(364, 132, 612, 404);
		contentPanel.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Iniciar Sesi\u00F3n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login ventanaLogin = new login();
				ventanaLogin.setLocationRelativeTo(null);
				ventanaLogin.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(838, 91, 127, 23);
		contentPanel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Principal.class.getResource("/images/user.png")));
		lblNewLabel_3.setBounds(802, 81, 37, 43);
		contentPanel.add(lblNewLabel_3);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
