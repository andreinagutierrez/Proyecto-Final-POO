package logico;

import java.util.ArrayList;

public class Plan {
	private int id;
	private String nombre;
	private ArrayList<Servicio> serviciosIncluidos;
	private double precioMensual;
	
	public Plan(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.serviciosIncluidos = new ArrayList<>();
		this.precioMensual = calcularPrecio();
	}
	
	public double calcularPrecio() {
		double total = 0;
		for(Servicio s: serviciosIncluidos) {
		    total += s.calcularPrecio();
		}
		if(serviciosIncluidos.size() >= 2) {
			total = total * 0.9;
		}
		return total; 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Servicio> getServiciosIncluidos() {
		return serviciosIncluidos;
	}
	public void agregarServicio(Servicio s) {
		serviciosIncluidos.add(s);
		this.precioMensual = calcularPrecio();
	}

	public double getPrecioMensual() {
		return precioMensual;
	}
	
	public boolean eliminarServicio(int id) {
		
		for(int i = 0; i < serviciosIncluidos.size(); i++) {
			if(serviciosIncluidos.get(i).getId() == id) {
				serviciosIncluidos.remove(i);
				this.precioMensual = calcularPrecio();
				return true;
			}
		}
		return false;
	}
	
	public boolean incluyeServicio(int idServicio) {
		for(Servicio s: serviciosIncluidos) {
			if(s.getId() == idServicio) {
				return true;
			}
		}
		return false;
	}
	
}
