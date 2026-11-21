package logico;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Movistar m = Movistar.cargarDatos();
		System.out.println("Test");
		for(Personal e: m.getLosEmpleados()) {
			System.out.println("Empleado: " + e.getNombre());
		}
		for(Plan p: m.getLosPlanes()) {
			System.out.println("Plan: " + p.getNombre());
			System.out.println("Precio Mensual: " + p.calcularPrecio());
			for(Servicio s: p.getServiciosIncluidos()) {
				System.out.println("Servicio: " + s.getNombre());
				System.out.println("Precio Unitario: " + s.calcularPrecio());
			}
		}
		//Cliente clientePrueba = new Cliente(1, "Jane", "Doe", "12312124","Manoguayabo",13124124);
		//m.registrarCliente(clientePrueba);
		//m.guardarDatos();
		for(Cliente c: m.getLosClientes()) {
			System.out.println("Cliente: " + c.getNombre());
		}
		File f = new File("movistar.dat");
		System.out.println("existe: " + f.exists());
		System.out.println("ruta completa: " + f.getAbsolutePath());

	}

}
