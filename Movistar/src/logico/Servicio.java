package logico;

import java.io.Serializable;

public abstract class Servicio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String nombre;
	protected double precioBase;
	
	public Servicio(int id, String nombre, double precioBase) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public abstract double calcularPrecio();
	
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

	public double getPrecioIndividual() {
		return precioBase;
	}

	public void setPrecioIndividual(double precioBase) {
		this.precioBase = precioBase;
	}

}
