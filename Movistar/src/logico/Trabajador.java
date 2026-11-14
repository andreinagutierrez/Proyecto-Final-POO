package logico;

import java.util.Date;

public class Trabajador extends Personal {
	private String areaEspecializada;
	private String turno;

	public Trabajador(int id, String nombre, String apellidos, String cedula, Date fechaNacimiento, String correo,
			String telefono, double salarioBase, boolean estado, String areaEspecializada, String turno) {
		super(id, nombre, apellidos, cedula, fechaNacimiento, correo, telefono, salarioBase, estado);
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
}
