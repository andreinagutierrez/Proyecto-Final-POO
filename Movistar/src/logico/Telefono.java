package logico;

public class Telefono extends Servicio{
	
	private static final long serialVersionUID = 1L;
	private String numTelefono;
	private int cantMinutos;
	
	public Telefono(int id, String nombre, double precioBase, String numTelefono, int cantMinutos) {
		super(id, nombre, precioBase);
		this.numTelefono = numTelefono;
		this.cantMinutos = cantMinutos;
	}
	
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
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
