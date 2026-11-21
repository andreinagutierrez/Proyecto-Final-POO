package logico;

public class Telefono extends Servicio{
	
	private static final long serialVersionUID = 1L;
	private int numTelefono;
	private int cantMinutos;
	
	public Telefono(int id, String nombre, double precioBase, int numTelefono, int cantMinutos) {
		super(id, nombre, precioBase);
		this.numTelefono = numTelefono;
		this.cantMinutos = cantMinutos;
	}
	
	public int getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}
	public int getCantMinutos() {
		return cantMinutos;
	}
	public void setCantMinutos(int cantMinutos) {
		this.cantMinutos = cantMinutos;
	}

	@Override
	public double calcularPrecio() {
		return precioBase + (cantMinutos * 3);
	}
	

}
