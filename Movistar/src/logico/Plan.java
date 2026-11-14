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
		return 0.0; //TODO
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
	
}
