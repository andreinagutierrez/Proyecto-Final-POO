package logico;

public class Internet extends Servicio{
	
	private int velocidadSubida;
	private int velocidadBajada;
	private String tipo;
	public Internet(int id, String nombre, double precioBase, int velocidadSubida, int velocidadBajada,
			String tipo) {
		super(id, nombre, precioBase);
		this.velocidadSubida = velocidadSubida;
		this.velocidadBajada = velocidadBajada;
		this.tipo = tipo;
	}
	
	
	public int getVelocidadSubida() {
		return velocidadSubida;
	}
	public void setVelocidadSubida(int velocidadSubida) {
		this.velocidadSubida = velocidadSubida;
	}
	public int getVelocidadBajada() {
		return velocidadBajada;
	}
	public void setVelocidadBajada(int velocidadBajada) {
		this.velocidadBajada = velocidadBajada;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public double calcularPrecio() {
		if(tipo.equalsIgnoreCase("Fibra")) {
			return precioBase + ((velocidadSubida + velocidadBajada) * 5);
		}else {
			return precioBase + ((velocidadSubida + velocidadBajada) * 3);
		}
	}
	
}
