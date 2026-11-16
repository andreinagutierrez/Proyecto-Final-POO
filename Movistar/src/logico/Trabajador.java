package logico;

import java.util.Date;

public class Trabajador extends Personal {
	private String areaEspecializada;
	private String turno;

	public Trabajador(int id, String nombre, String apellidos, String cedula, Date fechaNacimiento, String correo,
			String telefono, double salarioBase, boolean estado, Sucursal sucursal, String areaEspecializada, String turno) {
		super(id, nombre, apellidos, cedula, fechaNacimiento, correo, telefono, salarioBase, estado, sucursal);
		this.areaEspecializada = areaEspecializada;
		this.turno = turno;
	}

	public String getAreaEspecializada() {
		return areaEspecializada;
	}

	public void setAreaEspecializada(String areaEspecializada) {
		this.areaEspecializada = areaEspecializada;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public double calcularSalario() {
		double salario = this.salarioBase;

		if(this.turno.equalsIgnoreCase("Nocturno")) {
			double extraNocturno = this.salarioBase * 0.15;
			salario += extraNocturno;
		} else if (this.turno.equalsIgnoreCase("Feriado") || this.turno.equalsIgnoreCase("Fin de Semana")) {
			double diasExtra = this.salarioBase * 0.10;
		}

		return salario;
	}
}
