package logico;

public class Servicio {
	private int id;
	private String nombre;
	private double precioIndividual;
	
	public Servicio(int id, String nombre, double precioIndividual) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioIndividual = precioIndividual;
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

	public double getPrecioIndividual() {
		return precioIndividual;
	}

	public void setPrecioIndividual(double precioIndividual) {
		this.precioIndividual = precioIndividual;
	}

}
