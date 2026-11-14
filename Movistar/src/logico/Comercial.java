package logico;

import java.util.Date;

public class Comercial extends Personal {
	private double comision;
	private String zonaAsignada;
	private double ventasAcumuladas;

	public Comercial(int id, String nombre, String apellidos, String cedula, Date fechaNacimiento, String correo,
			String telefono, double salarioBase, boolean estado, Sucursal sucursal, double comision, String zonaAsignada,
			double ventasAcumuladas) {
		super(id, nombre, apellidos, cedula, fechaNacimiento, correo, telefono, salarioBase, estado, sucursal);
		this.comision = comision;
		this.zonaAsignada = zonaAsignada;
		this.ventasAcumuladas = ventasAcumuladas;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public String getZonaAsignada() {
		return zonaAsignada;
	}

	public void setZonaAsignada(String zonaAsignada) {
		this.zonaAsignada = zonaAsignada;
	}

	public double getVentasAcumuladas() {
		return ventasAcumuladas;
	}

	public void setVentasAcumuladas(double ventasAcumuladas) {
		this.ventasAcumuladas = ventasAcumuladas;
	}
}
