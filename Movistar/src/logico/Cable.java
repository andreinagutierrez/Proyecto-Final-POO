package logico;

public class Cable extends Servicio{
	
	private int cantCanales;
	
	public Cable(int id, String nombre, double precioBase, int cantCanales) {
		super(id, nombre, precioBase);
		this.cantCanales = cantCanales;
	}
	
	public int getCantCanales() {
		return cantCanales;
	}

	public void setCantCanales(int cantCanales) {
		this.cantCanales = cantCanales;
	}


	@Override
	public double calcularPrecio() {
		return precioBase + (cantCanales * 10);
	}
	
}
